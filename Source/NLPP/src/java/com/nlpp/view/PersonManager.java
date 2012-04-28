/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.view;

import com.nlpp.bo.PersonBO;
import com.nlpp.domain.Person;
import com.nlpp.domain.PersonChart;
import com.nlpp.helper.EncryptHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author SVN - Team
 */
public class PersonManager extends Person {

    private String passwordConfirm;
    private String passwordOld;
    private boolean isCheck = false;
    private boolean isCheckShow = false;
    private Person person;
    private boolean show;
    static int count = 0;

    /** Creates a new instance of PersonManager */
    public PersonManager() {
    }
    private PersonBO managerPerson;

    public PersonBO getManagerPerson() {
        return managerPerson;
    }
    private Person selectPerson;

    public Person getSelectPerson() {
        return selectPerson;
    }
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isIsCheckShow() {
        return isCheckShow;
    }

    public void setIsCheckShow(boolean isCheckShow) {
        this.isCheckShow = isCheckShow;
    }

    public boolean isIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public void setPasswordOld(String passwordOld) {
        this.passwordOld = passwordOld;
    }

    public boolean isShow() {
        System.out.println("Count: " + count);
        if (count % 2 == 0) {
            show = true;
            count = 1;
        } else {
            show = false;
            count = 0;
        }
        System.out.println("Count: " + count);
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void setSelectPerson(Person selectPerson) {
        this.selectPerson = selectPerson;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setManagerPerson(PersonBO managerPerson) {
        this.managerPerson = managerPerson;
    }
    private EncryptHelper eh = new EncryptHelper();

    public String login() {
        Person p = new Person();
        p = managerPerson.checkLogin(this.getEmail(), eh.encriptMd5Password(this.getPassword()));
        if (p != null) {
            System.out.println("Vao day khong");
            if (p.getRoles() != 0) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("login", p);
                return "index";
            }
        }
        return null;
    }

    public String loginHome() {
        Person p = new Person();
        p = managerPerson.checkLogin(this.getEmail(), eh.encriptMd5Password(this.getPassword()));
        if (p != null) {
            System.out.println("Vao day khong");
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            if (p.getRoles() == 0) {
                session.setAttribute("loginH", p);
                return "index";
            } else {
                session.setAttribute("login", p);
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "/admin/index";
            }
        } else {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            System.out.println(eh.encriptMd5Password(this.getPassword()));
            request.setAttribute("error", "Email or password is incorrect");
            return null;
        }
    }

    public String logoutHome() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("loginH");
        return "index";
    }

    public List<Person> getAllPerson() {
        return managerPerson.findAllByRoles(0);
    }

    public List<Person> getAllEmployee() {
        return managerPerson.findAllEmployee();
    }

    public Person returnPersonLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("login");
        return p;
    }

    public String editProfile() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("login");
        this.setPersonId(p.getPersonId());
        this.setPersonName(p.getPersonName());
        this.setEmail(p.getEmail());
        this.setPassword(p.getPassword());
        this.setPhone(p.getPhone());
        this.setAddress(p.getAddress());
        this.setGender(p.getGender());
        this.setRoles(p.getRoles());
        this.setBirthday(p.getBirthday());
        return "editProfile";
    }

    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("login");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../home/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PersonManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "login";
    }

    private void setError(String msg) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute("error", msg);
    }

    public String savePerson() {
        try {
            if (this.getPassword().isEmpty() || this.getPasswordConfirm().isEmpty()) {
                setError("Please enter password");
                return null;
            }
            if (!this.getPassword().equals(this.getPasswordConfirm())) {
                setError("Passwords do not match");
                return null;
            }
            Person p = new Person();
            p.setPersonName(this.getPersonName());
            p.setEmail(this.getEmail());
            p.setPassword(eh.encriptMd5Password(this.getPassword()));
            p.setBirthday(this.getBirthday());
            p.setGender(this.getGender());
            p.setPhone(this.getPhone());
            p.setRoles(this.getRoles());
            p.setAddress(this.getAddress());
            Date now = new Date();
            p.setDateCreate(now);
            p.setVisiable(0);
            boolean flag = managerPerson.savePerson(p);
            if (flag) {
                return "viewEmployee";
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        }
    }

    public String saveRegisterCustomer() {
        if (this.getPersonName().isEmpty() || this.getEmail().isEmpty() || this.getAddress().isEmpty() || this.getPhone().isEmpty()) {
            setError("Please fill in the information");
            return null;
        }
        if (this.getPassword().isEmpty() || this.getPasswordConfirm().isEmpty()) {
            setError("Please enter password");
            return null;
        }
        if (!this.getPassword().equals(this.getPasswordConfirm())) {
            setError("Passwords do not match");
            return null;
        }
        Person p = new Person();
        p.setPersonName(this.getPersonName());
        p.setEmail(this.getEmail());
        p.setPassword(eh.encriptMd5Password(this.getPassword()));
        p.setBirthday(this.getBirthday());
        p.setGender(this.getGender());
        p.setPhone(this.getPhone());
        p.setRoles(0);
        p.setAddress(this.getAddress());
        Date now = new Date();
        p.setDateCreate(now);
        p.setVisiable(0);
        Person flag = managerPerson.savePersons(p);
        if (flag != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("loginH", flag);
            return "index";
        } else {
            return null;
        }
    }
    Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

    public String removePerson() {
        int id = Integer.parseInt(m.get("id").toString());
        boolean flag = managerPerson.removePerson(id);
        return null;
    }

    public String editPersonById() {
        int id = Integer.parseInt(m.get("id").toString());
        String type = m.get("type").toString();
        Person p = managerPerson.findById(id);
        this.setPersonId(p.getPersonId());
        this.setPersonName(p.getPersonName());
        this.setEmail(p.getEmail());
        this.setPassword(p.getPassword());
        this.setPhone(p.getPhone());
        this.setAddress(p.getAddress());
        this.setGender(p.getGender());
        this.setRoles(p.getRoles());
        this.setBirthday(p.getBirthday());
        if (type.equals("1")) {
            return "editEmployee";
        } else if (type.equals("0")) {
            return "editCustomer";
        }
        return null;
    }

    public String updatePerson() {
        Person p = new Person();
        p.setPersonId(this.getPersonId());
        p.setPersonName(this.getPersonName());
        p.setEmail(this.getEmail());
        if (this.getPasswordConfirm().equals("") || this.getPasswordConfirm() == null) {
            p.setPassword(this.getPassword());
        } else {
            p.setPassword(eh.encriptMd5Password(this.getPasswordConfirm()));
        }
        p.setBirthday(this.getBirthday());
        p.setGender(this.getGender());
        p.setPhone(this.getPhone());
        p.setRoles(this.getRoles());
        p.setAddress(this.getAddress());
        boolean flag = managerPerson.updatePerson(p);
        if (flag) {
            String type = "";
            try {
                type = m.get("type").toString();
            } catch (Exception ex) {
            }
            if (type.equals("0")) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.removeAttribute("login");
                session.setAttribute("login", p);
                return null;
            }
            return "viewEmployee";
        }
        return null;
    }

    public String saveInforCustomer() {
        Person p = new Person();
        p.setPersonId(this.getPersonBySession().getPersonId());
        p.setPersonName(this.getPersonBySession().getPersonName());
        p.setPhone(this.getPersonBySession().getPhone());
        p.setGender(this.getPersonBySession().getGender());
        p.setBirthday(this.getPersonBySession().getBirthday());
        p.setAddress(this.getPersonBySession().getAddress());
        Person per = managerPerson.saveInformationPerson(p);
        if (per != null) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.removeAttribute("login");
            session.setAttribute("login", per);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull: ", "You are update successfull!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "You can not update their information!"));
        }
        return null;
    }

    public Person getPersonBySession() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        return p;
    }

    public String checkExistEmail() {
        Person flag = managerPerson.findByEmail(this.getEmail());
        if (flag != null) {
            msg = "Exist Email";
            isCheck = true;
            isCheckShow = false;
            person = flag;
        } else {
            msg = "Email is not exist!";
            isCheck = false;
            isCheckShow = true;
        }
        return null;
    }

    public String updateAvatar() {
        boolean iCheck = isCheckHasFile();
        if (iCheck) {
            String fileName = handleFileUpload();
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            Person p = (Person) session.getAttribute("loginH");
            p.setImages(fileName);
            isCheck = managerPerson.updateAvatar(p);
            if (iCheck) {
                session.removeAttribute("loginH");
                session.setAttribute("loginH", p);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull:", "Update Avatar Successfull"));
                System.out.println("Thành Công");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", " You are can not update avatar!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", " You are can not update avatar!"));
        }
        return null;
    }

    public String handleFileUpload() {
        String fileName = "";
        String uploadedFolder = "images/avatar";
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        File result = new File(extContext.getRealPath("//" + uploadedFolder) + "//" + file.getFileName());
        System.out.println(result.getAbsolutePath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);

            byte[] buffer = new byte[BUFFER_SIZE];

            int bulk;
            InputStream inputStream = file.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();

            filePath = uploadedFolder + "/" + file.getFileName();
            fileName = file.getFileName();
            System.out.println("Upload File: " + fileName);
        } catch (IOException e) {
            System.out.println("Exception:" + e.getMessage());
            fileName = "";
        }
        return fileName;
    }
    private String filePath = "";
    private static final int BUFFER_SIZE = 6124;
    private UploadedFile file;

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private boolean isCheckHasFile() {
        try {
            String str = file.getFileName();
            if (str.isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
    }

    public String saveChangePassword() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Person p = (Person) session.getAttribute("loginH");
        if (p.getPassword().equals(eh.encriptMd5Password(this.getPasswordOld()))) {
            if (this.getPassword().equals(this.getPasswordConfirm())) {
                p.setPassword(eh.encriptMd5Password(this.getPassword()));
                boolean flag = managerPerson.saveChangePassword(p);
                if (flag) {
                    session.removeAttribute("loginH");
                    session.setAttribute("loginH", p);
                    this.setPassword("");
                    this.setPasswordConfirm("");
                    this.setPasswordOld("");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull: ", "You are update successfull!"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "You are can not change password!"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "Password new and password new confirm not true!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", "Password Old not true!"));
        }
        return null;
    }
    private CartesianChartModel personModel;

    public CartesianChartModel getPersonModel() {
        personModel = new CartesianChartModel();
        ChartSeries pChart = new ChartSeries();
        pChart.setLabel("Person");
        List<Person> listOfUser = managerPerson.findAll();
        if (listOfUser.size() >= 1) {
            for (int i = 1; i <= 12; i++) {
                int count = 0;
                for (int j = 0; j < listOfUser.size(); j++) {
                    Person p = listOfUser.get(j);
                    if (p.getDateCreate().getMonth() == i - 1) {
                        count++;
                    }
                }
                pChart.set(String.valueOf(i), count);
            }
        }
        personModel.addSeries(pChart);
        return personModel;
    }
}
