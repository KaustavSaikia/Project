package com.astro.testing.map;

/**
 * This Enum is to keep all the locators of Astro used in this project. Locator variables in
 * Enum should be appended with below (upper case):- </br>
 * _ID </br>
 * _NAME </br> 
 * _CLASS </br>
 * _CSS </br>
 * _LINK </br>
 * _PLINK </br>
 * _TAG </br>
 * _XPATH </br>
 */
public enum ObjectMap {

	//----- Landing Page ---
	CONTINUE_TO_ASTRO_ID("continue"),
	
	//----- Home Page -----
	HOME_PAGE_SIDE_IMAGE_CSS(".slideimage"),
	HOME_PAGE_SITE_FOOTER_WRAPPER_CSS("#acm_footer>.footerwrapper"),
	HOME_PAGE_FLEX_NAV_CSS(".flex-control-nav.flex-control-paging>li"),
	HOME_PAGE_FLEX_NAV_NEXT_CSS(".flex-next"),
	HOME_PAGE_FLEX_NAV_DOT_XPATH("//ol/li[%s]/a"),
	HOME_PAGE_ACTIVE_FLEX_HEADER_XPATH("//li[@class='flex-active-slide']//div/h2"),
	HOME_PAGE_ACTIVE_FLEX_LINK_XPATH("//li[@class='flex-active-slide']/a"),
	
	//------ Astro Go Page ------
	ASTRO_GO_PAGE_BANNER_TITLE_CSS(".astrogo-title"),
	
	//------ Registration Page ----
	REGISTRATION_FIRST_NAME_CSS("input.txtfirstname"),
	REGISTRATION_LAST_NAME_CSS("input.txtlastname"),
	REGISTRATION_ASTRO_ID_CSS("input.txtastroid"),
	REGISTRATION_EMAIL_CSS("input.txtemail"),
	REGISTRATION_PASSWORD_CSS("input.txtpassword"),
	REGISTRATION_I_AGREE_SIGNUP_BTN_ID("btnSubmit"),
	REGISTRATION_I_AGREE_SIGNUP_BTN_XPATH("//button[@id='btnSubmit' and contains(text(),'I agree')]"),
	REGISTRATION_COMPLETE_MESSAGE_XPATH("//div[@class='container']"),
	
	REGISTRATION_EMAIL_VALIDATION_ERROR_XPATH("//span[@data-bind='html: $data.valmessage.email()']"),
	
	//------ Common Header ------
	ASTRO_REGISTER_ID("acmregister"),
	
	
	;

    ObjectMap(String enumValue) {
        this.enumValue = enumValue;
    }

    private String enumValue;

    public String getEnumValue() {
        return toString();
    }

    public String toString() {
        return this.enumValue;
    }
}