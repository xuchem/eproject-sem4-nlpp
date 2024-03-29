/***************************** PLUGINS *********************************/

/**
 * Cookie plugin
 *
 * Copyright (c) 2006 Klaus Hartl (stilbuero.de)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 */

/**
 * Create a cookie with the given name and value and other optional parameters.
 *
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain
 *       used when the cookie was set.
 *
 * @param String name The name of the cookie.
 * @param String value The value of the cookie.
 * @param Object options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object.
 *                             If a negative value is specified (e.g. a date in the past), the cookie will be deleted.
 *                             If set to null or omitted, the cookie will be a session cookie and will not be retained
 *                             when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will
 *                        require a secure protocol (like HTTPS).
 * @type undefined
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */

/**
 * Get the value of a cookie with the given name.
 *
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 *
 * @param String name The name of the cookie.
 * @return The value of the cookie.
 * @type String
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
/******  End Cookie Plugin ******/

/******  Simple Spy plugin (based on a tutorial from jqueryfordesigners.com) ******/
(function ($) {
	$.fn.simpleSpy = function (limit, interval) {
	limit = limit || 4;
	interval = interval || 4000;
	
	return this.each(function () {
		// 1. setup
			// capture a cache of all the list items
			// chomp the list down to limit li elements
		var $list = $(this),
		items = [], // uninitialised
		currentItem = limit,
		total = 0, // initialise later on
		height = $list.find('> li:first').height();
			
		// capture the cache
		$list.find('> li').each(function () {
			items.push('<li>' + $(this).html() + '</li>');
		});
		
		total = items.length;
		
		$list.wrap('<div class="members-list-wrap" />');
		
		$list.find('> li').filter(':gt(' + (limit - 1) + ')').remove();

		// 2. effect        
		function spy() {
			// insert a new item with opacity and height of zero
			var $insert = $(items[currentItem]).css({
				height : 0,
				opacity : 0,
				display : 'none'
			}).prependTo($list);
						
			// fade the LAST item out
			$list.find('> li:last').animate({ opacity : 0}, 1000, function () {
				// increase the height of the NEW first item
				$insert.animate({ height : height }, 1000).animate({ opacity : 1 }, 1000);
				
				// AND at the same time - decrease the height of the LAST item
				// $(this).animate({ height : 0 }, 1000, function () {
					// finally fade the first item in (and we can remove the last)
					$(this).remove();
				// });
			});
			
			currentItem++;
			if (currentItem >= total) {
				currentItem = 0;
			}
			
			setTimeout(spy, interval)
		}
		
		spy();
	});
};	
})(jQuery);
/******  End Simple Spy plugin ******/

/***************************** END PLUGINS *********************************/



/*************************** CUSTOM FUNCTIONS ******************************/
$(function () {
	$('ul.members-list').simpleSpy(); // Apply Simple Spy plugin to the members-list ul
});

$(function() {
	$("#datepicker").datepicker(); // Apply JQuery UI's datepicker plugin
	$("#accordion").accordion({collapsible: true, active:false});  // Apply JQuery UI's accordion plugin
});
	
$(function() {
	$("#sortable, #sortable2").sortable(); // Apply JQuery UI's sortable plugin
	$("#sortable, #sortable2").disableSelection();
	$("#tabs").tabs().find(".ui-tabs-nav").sortable({axis:'x'}); // JQuery UI's Tabs horizontal sorting
});

$(function() {
	// Apply portlets of JQuery UI's sortable plugin (dashboard page widgets)	   
	$(".column").sortable({
		connectWith: '.column', 
		handle: '.portlet-header',
		stop:function(){
			/* add the order from columns to acomma seperated string*/
			var order="";
			$(".column").children(".portlet").each(function(){
					order+=$(this).attr("id")+",";
			});
			order=order.substring(0,order.length-1);
			
			//alert(order);
			/**
			 * AJAX Post order data the php page to save the ordered items. 
			 */
			//$.post('order-items.php',order+'&action=updateOrder');
		}

	});
	
	
	$(".portlet").addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
		.find(".portlet-header")
			.addClass("ui-widget-header ui-corner-all")
			.prepend('<span class="ui-icon ui-icon-minusthick"></span>')
			.end()
		.find(".portlet-content");

	$(".portlet-header .ui-icon").click(function() {
		$(this).toggleClass("ui-icon-minusthick").toggleClass("ui-icon-plusthick");
		$(this).parents(".portlet:first").find(".portlet-content").toggle();
		
	});

	$(".column").disableSelection();

});

$(function() {
	//scrollpane parts
	var scrollPane = $('.scroll-pane');
	var scrollContent = $('.scroll-content');
	
	//build slider
	var scrollbar = $(".scroll-bar").slider({
		slide:function(e, ui){
			if( scrollContent.width() > scrollPane.width() ){ scrollContent.css('margin-left', Math.round( ui.value / 100 * ( scrollPane.width() - scrollContent.width() )) + 'px'); }
			else { scrollContent.css('margin-left', 0); }
		}
	});
	
	//append icon to handle
	var handleHelper = scrollbar.find('.ui-slider-handle')
	.mousedown(function(){
		scrollbar.width( handleHelper.width() );
	})
	.mouseup(function(){
		scrollbar.width( '100%' );
	})
	.append('<span class="ui-icon ui-icon-grip-dotted-vertical"></span>')
	.wrap('<div class="ui-handle-helper-parent"></div>').parent();
	
	//change overflow to hidden now that slider handles the scrolling
	scrollPane.css('overflow','hidden');
	
	//size scrollbar and handle proportionally to scroll distance
	function sizeScrollbar(){
		var remainder = scrollContent.width() - scrollPane.width();
		var proportion = remainder / scrollContent.width();
		var handleSize = scrollPane.width() - (proportion * scrollPane.width());
		scrollbar.find('.ui-slider-handle').css({
			width: handleSize,
			'margin-left': -handleSize/2
		});
		handleHelper.width('').width( scrollbar.width() - handleSize);
	}

//reset slider value based on scroll content position
function resetValue(){
	var remainder = scrollPane.width() - scrollContent.width();
	var leftVal = scrollContent.css('margin-left') == 'auto' ? 0 : parseInt(scrollContent.css('margin-left'));
	var percentage = Math.round(leftVal / remainder * 100);
	scrollbar.slider("value", percentage);
}
//if the slider is 100% and window gets larger, reveal content
function reflowContent(){
		var showing = scrollContent.width() + parseInt( scrollContent.css('margin-left') );
		var gap = scrollPane.width() - showing;
		if(gap > 0){
			scrollContent.css('margin-left', parseInt( scrollContent.css('margin-left') ) + gap);
		}
}

//change handle position on window resize
$(window)
.resize(function(){
		resetValue();
		sizeScrollbar();
		reflowContent();
});
//init scrollbar size
setTimeout(sizeScrollbar,10);//safari wants a timeout
});

$(document).ready(function(){
	
	/********** THEME SWITCHER ***********/
	
	/* cookie vars */
	var cookie_name = "real_world_admin_theme";
	var cookie_options = { path: '/', expires: 7 };
	
	var get_cookie = $.cookie('real_world_admin_theme');
	if(get_cookie == null) // If no cookie is set apply default theme
	{
		get_cookie = 'clean-grey';
	}
	$("#active-theme-globals").attr({ href: "themes/" + get_cookie + "/css/globals.css"});
	$("#active-theme-commons").attr({ href: "themes/" + get_cookie + "/css/commons.css"});
	$("#active-theme-plugins").attr({ href: "themes/" + get_cookie + "/css/plugins.css"});
	
	$(".theme-frame :radio").click(function() {
		// Switch theme based on user's choice (via 'themes' page's radio button)
		var themename = $(this).attr("id");		
		$("#active-theme-globals").attr({ href: "themes/" + themename + "/css/globals.css"});
		$("#active-theme-commons").attr({ href: "themes/" + themename + "/css/commons.css"});
		$("#active-theme-plugins").attr({ href: "themes/" + themename + "/css/plugins.css"});
		
		$.cookie('real_world_admin_theme', themename, cookie_options);
		return false;
	});
	/******** END THEME SWITCHER *********/	
	
	/******** LAYOUT CHANGER *********/	
	var get_cookie = $.cookie('real_world_admin_layout'); // Get cookie
	
	// If no cookie is set or cookie is set to 'fixed', apply corresponding styles
	if(get_cookie == null || get_cookie == 'fixed') 
	{
		$('#container, #footer').css("width", "960px");	
	}
	// Else set styles for liquid layout
	else
	{
		$('#container, #footer').css("width", "100%");	
	}
	
	$("#liquid-layout").click(function(){
		$('#container, #footer').css("width", "100%");
		$.cookie('real_world_admin_layout', 'liquid', cookie_options);
	});
	$("#fixed-layout").click(function(){	
		$('#container, #footer').css("width", "960px");	
		$.cookie('real_world_admin_layout', 'fixed', cookie_options);
	});
	/******** END LAYOUT CHANGER *********/	
 					   						   
	/******** TOP MENU *********/					   
	$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled - Adds empty span tag after ul.subnav
	
	$('.topnav li').hover(
		function () {
			//show its submenu
			$('ul', this).slideDown(100);

		}, 
		function () {
			//hide its submenu
			$('ul', this).slideUp(100);			
		}
	);
	/******* END TOP MENU ********/			
	
	/* Table's delete button click event */
	$(".delete-record").click(function(evented){
		$(evented).preventDefault; // Prevent Link's default behaviour				
		var element = $(this);
		/* Get Confirmation */
		if( confirm('The record will be deleted permanently. Do you want to continue?'))
		{
			/* Hide the deleted row with fadeout effect */
			element.parent().parent().eq(0).fadeOut("slow");
			/* Assign Success Message to status container and hide after a while. */
			$("#status").html('<div class="status success">The record was deleted succesfully.</div>').show('slow').delay(2000).hide('slow');
		}
		
		return false;

	});
	
	/* Uncomment This Block if you want info messages(success, error, etc..) to disappear on click  */
	
	//$(".status").click(function(){$(this).hide('slow')});
	
	/* Event attached to 'Add Page' Button on 'Sample Page' Page. Opens a popup dialog */
	$('#add-page').click(function(e) {
		e.preventDefault();
		$.nyroModalManual({url:'./demo-files/dialog.html', width:'420', height:'550'});
		return false;
	});
	
	/* Make Tables match JQuery UI's theme's style */
	$(".list-table thead").addClass("ui-widget-header ui-corner-all");
	$(".list-table tbody td").each(function(){
		$(this).addClass("ui-widget-content");
	});
	$(".list-table tr").hover(
		function()
		{
			$(this).children("td").addClass("ui-state-hover");
		},
		function()
		{
			$(this).children("td").removeClass("ui-state-hover");
		}
	);
	$(".list-table tbody tr:odd").addClass("odd");	
});
/*********************** END CUSTOM FUNCTIONS ***************************/

