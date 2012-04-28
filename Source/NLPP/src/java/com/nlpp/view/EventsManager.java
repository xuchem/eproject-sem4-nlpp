/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nlpp.view;

import com.nlpp.bo.EmailRecieverEventCategoryBO;
import com.nlpp.bo.EventsBO;
import com.nlpp.domain.Category;
import com.nlpp.domain.EnrollEvent;
import com.nlpp.domain.Events;
import com.nlpp.helper.EmailHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author SVN - Team
 */
public class EventsManager extends Events {

    /** Creates a new instance of EventsManager */
    public EventsManager() {
    }
    private int catID = 0;
    private EventsBO managerEvents;
    private EmailRecieverEventCategoryBO managerEmail;
    private Events event;
    private Map m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private String filePath = "";
    private static final int BUFFER_SIZE = 6124;
    private UploadedFile file;

    public EmailRecieverEventCategoryBO getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(EmailRecieverEventCategoryBO managerEmail) {
        this.managerEmail = managerEmail;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public int getCatID() {
        return catID;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public EventsBO getManagerEvents() {
        return managerEvents;
    }

    public void setManagerEvents(EventsBO managerEvents) {
        this.managerEvents = managerEvents;
    }

    public List<Events> getAllEvents() {
        return managerEvents.findAll();
    }

    public List<Events> getAllEvent() {
        if (m.get("catId") != null) {
            int id = Integer.parseInt(m.get("catId").toString());
            return managerEvents.findAll(id);
        } else {
            return managerEvents.findAll();
        }
    }

    public List<Events> getAllEventsById() {
        int id = Integer.parseInt(m.get("id").toString());
        return managerEvents.findAll(id);
    }

    public List<EnrollEvent> getAllPersonByEventId() {
        List<EnrollEvent> erList = new ArrayList<EnrollEvent>();
        System.out.println("ID:" + m.get("id"));
        try {
            int id = Integer.parseInt(m.get("id").toString());
            Events e = managerEvents.findById(id);
            Set<EnrollEvent> s = e.getEnrollEvents();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                EnrollEvent object = (EnrollEvent) it.next();
                erList.add(object);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
        return erList;
    }

    public Events getEventsById() {
        String idx = m.get("id").toString();
        if (!idx.equals("")) {
            int id = Integer.parseInt(idx);
            event = managerEvents.findById(id);
            return event;
        } else {
            return null;
        }
    }

    public Events getEventsByIdPerson() {
        String idx = m.get("id").toString();
        if (!idx.equals("")) {
            int id = Integer.parseInt(idx);
            event = managerEvents.findById(id);
            return event;
        } else {
            return null;
        }
    }

    public Events getUpdateViewEventsById() {
        String idx = m.get("id").toString();
        if (!idx.equals("")) {
            int id = Integer.parseInt(idx);
            boolean flag = managerEvents.updateViewCount(id);
            event = managerEvents.findById(id);
            return event;
        } else {
            return null;
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String editEventsById() {
        int id = Integer.parseInt(m.get("id").toString());
        String type = m.get("type").toString();
        event = managerEvents.findById(id);
        catID = event.getCategory().getCatId();
        this.setEventName(event.getEventName());
        this.setEventId(event.getEventId());
        this.setCountView(event.getCountView());
        this.setDeadline(event.getDeadline());
        this.setTimeEvent(event.getTimeEvent());
        this.setDescription(event.getDescription());
        this.setDetails(event.getDetails());
        this.setImages(event.getImages());
        this.setAddress(event.getAddress());
        this.setPrice(event.getPrice());
        if (type.equals("0")) {
            return "editEvents";
        } else if (type.equals("1")) {
            return "addNewEnrollEvents";
        }
        return null;
    }
    private EmailHelper helper = new EmailHelper();

    public String saveEvents() {
        boolean flag = isCheckHasFile();
        if (flag) {
            String fileName = this.handleFileUpload();
            if (fileName != null || !fileName.equals("")) {
                Events evt = new Events();
                evt.setEventName(this.getEventName());
                evt.setDeadline(this.getDeadline());
                evt.setTimeEvent(this.getTimeEvent());
                evt.setPrice(this.getPrice());
                Category cat = new Category();
                cat.setCatId(catID);
                evt.setCategory(cat);
                Date now = new Date();
                evt.setDateCreate(now);
                evt.setDescription(this.getDescription());
                evt.setDetails(this.getDetails());
                evt.setAddress(this.getAddress());
                evt.setImages(fileName);
                evt.setVisiable(0);
                Events ev = managerEvents.saveEvent(evt);
                List<String> aListEmail = managerEmail.findEmailByCategoryId(catID);
                try {
                    if (aListEmail.size() > 0) {
                        String contents = ev.getDetails() + "<br /><br /><br /><center>http://localhost:8084/NLPP/home/viewDetailsEvent.xhtml?id=" + ev.getEventId() + "</center><br /><br /><br />";
                        helper.sendMail(ev.getEventName(), aListEmail, contents);
                    }
                } catch (Exception e) {
                    System.out.println("Not Network:" + e.getMessage());
                }
                return "viewEvents";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public String removeEvents() {
        int id = Integer.parseInt(m.get("id").toString());
        managerEvents.removeEvents(id);
        return null;
    }

    public String updateEvents() {
        boolean isCheck = isCheckHasFile();
        if (isCheck) {
            String fileName = this.handleFileUpload();
            if (fileName != null || !fileName.equals("")) {
                System.out.println("Cat ID:" + catID);
                int id = Integer.parseInt(m.get("id").toString());
                Category cat = new Category();
                cat.setCatId(catID);
                Events e = new Events();
                e.setCategory(cat);
                e.setEventId(id);
                e.setPrice(this.getPrice());
                e.setImages(fileName);
                e.setEventName(this.getEventName());
                e.setAddress(this.getAddress());
                e.setDeadline(this.getDeadline());
                e.setTimeEvent(this.getTimeEvent());
                e.setDetails(this.getDetails());
                e.setDescription(this.getDescription());
                boolean flag = managerEvents.updateEvents(e);
                if (flag) {
                    return "viewEvents";
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            System.out.println("Cat ID:" + catID);
            int id = Integer.parseInt(m.get("id").toString());
            Category cat = new Category();
            cat.setCatId(catID);
            Events e = new Events();
            e.setCategory(cat);
            e.setEventId(id);
            e.setPrice(this.getPrice());
            e.setImages(this.getImages());
            e.setEventName(this.getEventName());
            e.setAddress(this.getAddress());
            e.setDeadline(this.getDeadline());
            e.setTimeEvent(this.getTimeEvent());
            e.setDetails(this.getDetails());
            e.setDescription(this.getDescription());
            boolean flag = managerEvents.updateEvents(e);
            if (flag) {
                return "viewEvents";
            } else {
                return null;
            }
        }
    }

    public String handleFileUpload() {
        String fileName = "";
        String uploadedFolder = "images/events";
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

    public List<Events> getAllTop10() {
        return managerEvents.findAllTop10();
    }
    private CartesianChartModel eventModel;

    public CartesianChartModel getEventModel() {
        eventModel = new CartesianChartModel();
        ChartSeries pChart = new ChartSeries();
        pChart.setLabel("Person");
        List<Events> aListEvtChat = managerEvents.findAll();
        if (aListEvtChat.size() >= 1) {
            for (int i = 1; i <= 12; i++) {
                int count = 0;
                for (int j = 0; j < aListEvtChat.size(); j++) {
                    Events p = aListEvtChat.get(j);
                    if (p.getDateCreate().getMonth() == i-1) {
                        count++;
                    }
                }
                pChart.set(String.valueOf(i), count);
            }
        }
        eventModel.addSeries(pChart);
        return eventModel;
    }
}
