package com.e_com.Constants;

public interface ApplicationMessageConstants {
	public interface ServiceErrorMessages {

		//jwt Security
		String EX_JWT_INVALID = "ex.jwt.invalid";
		String EX_JWT_EXPIRED = "ex.jwt.expired";

		String ERR_SAVE_USER_DETAILS = "err.save.user.details";
		String EX_SAVE_USER_DETAILS = "ex.save.user.details";
		String ERR_SAVE_USER_ROLE_DETAILS = "err.save.user.role.details";
		String EX_SAVE_USER_ROLE_DETAILS = "ex.save.user.role.details";
		String ERR_FORGOT_PASSWORD_EMAIL = "err.forgot.password.email";
		String EX_FORGOT_PASSWORD_EMAIL = "ex.forgot.password.email";
		String ERR_RESET_PASSWORD = "err.reset.password";
		String EX_RESET_PASSWORD = "ex.reset.password";
		String ERR_LOGIN_USER_LOGS_DETAILS = "err.login.userlogs.detail";
		String EX_LOGIN_USER_LOGS_DETAILS = "ex.login.userlogs.detail";
		String ERR_SAVE_USER_LOGS_DETAILS = "err.save.userlogs.details";
		String EX_SAVE_USER_LOGS_DETAILS = "ex.save.userlogs.details";
		String ERR_RETRIEVE_USER_LOGS_DETAILS = "err.get.all.page.user.logs.details";
        String EX_RETRIEVE_USER_LOGS_DETAILS = "ex.get.all.page.user.logs.details";
        String ERR_RETRIEVE_ALL_USERROLE_DETAILS = "err.get.all.user.details";
		String EX_RETRIEVE_ALL_USERROLE_DETAILS = "ex.get.all.user.details";
		String ERR_RETRIEVE_ALL_USER_DETAILS = "err.get.all.user.details";
		String EX_RETRIEVE_ALL_USER_DETAILS = "ex.get.all.user.details";
		String ERR_RETRIEVE_USER_BY_NAME = "err.get.all.user.details.by.name";
		String EX_RETRIEVE_USER_BY_NAME = "ex.get.all.user.details.by.name";
		String ERR_RETRIEVE_USER_BY_ID = "err.get.all.user.details.by.id";
		String EX_RETRIEVE_USER_BY_ID = "ex.get.all.user.details.by.id";
		String ERR_RETRIEVE_USER_BY_ROLE = "err.get.all.user.details.by.role";
		String EX_RETRIEVE_USER_BY_ROLE = "ex.get.all.user.details.by.role";
		String ERR_UPDATE_RE_USER_DETAILS = "err.update.user.details";
		String EX_UPDATE_RE_USER_DETAILS = "ex.update.user.details";
		String ERR_UPDATE_USER_STATUS = "err.update.user.status";
		String EX_UPDATE_USER_STATUS = "ex.update.user.status";
		String ERR_UPDATE_USER_PASSWORD = "err.update.user.password";
        String EX_UPDATE_USER_PASSWORD = "ex.update.user.password";
        String ERR_RETRIEVE_USER_BY_EMAIL_ADDRESS = "err.retrieve.user.by.email.address";
		String EX_RETRIEVE_USER_BY_EMAIL_ADDRESS = "ex.retrieve.user.by.email.address";
		String ERR_SAVE_BRAND_DETAILS = "err_save_brand_details";
        String EX_SAVE_BRAND_DETAILS = "ex_save_brand_details";
        String ERR_UPDATE_BRAND_DETAILS = "err_update_brand_details";
        String EX_UPDATE_BRAND_DETAILS = "ex_update_brand_details";
        String ERR_UPDATE_BRAND_STATUS = "err_update_brand_status";
        String EX_UPDATE_BRAND_STATUS = "ex_update_brand_status";
        String ERR_RETRIEVE_ALL_BRAND_DETAILS = "err_retrive_all_brand_details";
        String EX_RETRIEVE_ALL_BRAND_DETAILS = "ex_retrive_all_brand_details";
		String ERR_SAVE_BRANCH_DETAILS = "err.save.branch.details";
		String EX_SAVE_BRANCH_DETAILS = "ex.save.branch.details";
		String ERR_RETRIEVE_ALL_BRANCH_DETAILS = "err.get.all.branch.details";
		String EX_RETRIEVE_ALL_BRANCH_DETAILS = "ex.get.all.branch.details";
		String ERR_RETRIEVE_PAGINATED_BRANCH_DETAILS = "err.get.paginated.branch.details";
		String EX_RETRIEVE_PAGINATED_BRANCH_DETAILS = "ex.get.paginated.branch.details";
		String ERR_UPDATE_BRANCH_DETAILS = "err.update.branch.details";
		String EX_UPDATE_BRANCH_DETAILS = "ex.update.branch.details";
		String ERR_RETRIEVE_BRANCH_BY_NAME = "err.get.by.branch.name";
		String EX_RETRIEVE_BRANCH_BY_NAME = "ex.get.by.branch.name";
		String ERR_UPDATE_BRANCH_STATUS = "err.update.branch.status";
		String EX_UPDATE_BRANCH_STATUS = "ex.update.branch.status";
		String ERR_RETRIEVE_BRANCH_BY_ID ="err.get.by.branch.id";
		String EX_RETRIEVE_BRANCH_BY_ID="ex.get.by.branch.id";
		String ERR_SAVE_POSTAGE_PARTNER_DETAILS = "err.save.postage.partner.details";
        String EX_SAVE_POSTAGE_PARTNER_DETAILS = "ex.save.postage.partner.details";
        String ERR_UPDATE_POSTAGE_PARTNER_DETAILS = "err.update.postage.partner.details";
        String EX_UPDATE_POSTAGE_PARTNER_DETAILS = "ex.update.postage.partner.details";
        String ERR_UPDATE_POSTAGE_PARTNER_STATUS = "err.update.postage.partner.status";
        String EX_UPDATE_POSTAGE_PARTNER_STATUS = "ex.update.postage.partner.status";
        String ERR_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS = "err.retrive.all.postage.partner.details";
        String EX_RETRIEVE_ALL_POSTAGE_PARTNER_DETAILS = "ex.retrive.all.postage.partner.details";
		String ERR_SAVE_CONDITIONS_DETAILS = "err_save_conditions_details";
		String EX_SAVE_CONDITIONS_DETAILS = "ex_save_conditions_details";
		String ERR_UPDATE_CONDITIONS_DETAILS = "err_update_conditions_details";
		String EX_UPDATE_CONDITIONS_DETAILS = "ex_update_conditions_details";
		String ERR_UPDATE_CONDITIONS_STATUS = "err_update_conditions_status";
		String EX_UPDATE_CONDITIONS_STATUS = "ex_update_conditions_status";
		String ERR_RETRIEVE_ALL_CONDITIONS_DETAILS= "err.get.all.conditions.details";
		String EX_RETRIEVE_ALL_CONDITIONS_DETAILS= "ex.get.all.conditions.details";
		String ERR_SAVE_STATUS_DETAILS = "err_save_status_details";
		String EX_SAVE_STATUS_DETAILS = "ex_save_status_details";
		String ERR_SAVE_BANK_DETAILS = "err_save_bank_details";
		String EX_SAVE_BANK_DETAILS = "ex_save_bank_details";
		String ERR_UPDATE_STATUS_DETAILS = "err_update_status_details";
		String EX_UPDATE_STATUS_DETAILS = "ex_update_status_details";
		String ERR_UPDATE_FOR_STATUS = "err_update_status_status";
		String EX_UPDATE_FOR_STATUS = "ex_update_status_status";
		String ERR_SAVE_SHIPPING_ADDRESS_DETAILS = "err.save.shipping.address.details";
        String EX_SAVE_SHIPPING_ADDRESS_DETAILS = "ex.save.shipping.address.details";
        String ERR_UPDATE_SHIPPING_ADDRESS_DETAILS = "err.update.shipping.address.details";
        String EX_UPDATE_SHIPPING_ADDRESS_DETAILS = "ex.update.shipping.address.details";
        String ERR_UPDATE_SHIPPING_ADDRESS_STATUS = "err.update.shipping.address.status";
        String EX_UPDATE_SHIPPING_ADDRESS_STATUS = "ex.update.shipping.address.status";
        String ERR_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS = "err.retrieve.all.shipping.address.details";
        String EX_RETRIEVE_ALL_SHIPPING_ADDRESS_DETAILS = "ex.retrieve.all.shipping.address.details";
		String ERR_SAVE_USER_BANK_DETAILS = "err.save.user.bank.details";
        String EX_SAVE_USER_BANK_DETAILS = "ex.save.user.bank.details";
        String ERR_UPDATE_USER_BANK_DETAILS = "err.update.user.bank.details";
        String EX_UPDATE_USER_BANK_DETAILS = "ex.update.user.bank.details";
        String ERR_UPDATE_USER_BANK_STATUS = "err.update.user.bank.status";
        String EX_UPDATE_USER_BANK_STATUS = "ex.update.user.bank.status";
        String ERR_RETRIEVE_ALL_USER_BANK_DETAILS = "err.retrive.all.user.bank.details";
        String EX_RETRIEVE_ALL_USER_BANK_DETAILS = "ex.retrive.all.user.bank.details";
		String ERR_RETRIEVE_ALL_STATUS_DETAILS = "err.get.all.status.details";
		String EX_RETRIEVE_ALL_STATUS_DETAILS = "ex.get.all.status.details";
        String ERR_SAVE_PRODUCT_DETAILS = "err.save.product.details";
        String EX_SAVE_PRODUCT_DETAILS = "ex.save.product.details";
        String ERR_UPDATE_PRODUCT_DETAILS = "err.update.product.details";
        String EX_UPDATE_PRODUCT_DETAILS = "ex.update.product.details";
        String ERR_UPDATE_PRODUCT_STATUS = "err.update.product.status";
        String EX_UPDATE_PRODUCT_STATUS = "ex.update.product.status";
        String ERR_RETRIEVE_ALL_PRODUCT_DETAILS = "err.retrieve.all.product.details";
        String EX_RETRIEVE_ALL_PRODUCT_DETAILS = "ex.retrieve.all.product.details";
        String ERR_SAVE_ORDERS_DETAILS = "err.save.orders.details";
        String EX_SAVE_ORDERS_DETAILS = "ex.save.orders.details";
        String ERR_UPDATE_ORDERS_DETAILS = "err.update.orders.details";
        String EX_UPDATE_ORDERS_DETAILS = "ex.update.orders.details";
        String ERR_UPDATE_ORDERS_STATUS = "err.update.orders.status";
        String EX_UPDATE_ORDERS_STATUS = "ex.update.orders.status";
        String ERR_RETRIEVE_ALL_ORDERS_DETAILS = "err.retrieve.all.orders.details";
        String EX_RETRIEVE_ALL_ORDERS_DETAILS = "ex.retrieve.all.orders.details";
		String ERR_SAVE_ORDER_TRACKING_DETAILS = "err.save.order.tracking.details";
		String EX_SAVE_ORDER_TRACKING_DETAILS = "ex.save.order.tracking.details";
		String ERR_UPDATE_ORDER_TRACKING_DETAILS = "err.update.order.tracking.details";
		String EX_UPDATE_ORDER_TRACKING_DETAILS = "ex.update.order.tracking.details";
		String ERR_UPDATE_ORDER_TRACKING_STATUS = "err.update.order.tracking.status";
		String EX_UPDATE_ORDER_TRACKING_STATUS = "ex.update.order.tracking.status";
		String ERR_RETRIEVE_ALL_ORDER_TRACKING_DETAILS = "err.retrieve.all.order.tracking.details";
		String EX_RETRIEVE_ALL_ORDER_TRACKING_DETAILS = "ex.retrieve.all.order.tracking.details";
		String ERR_SAVE_PAYMENT_DETAILS = "err.save.payment.details";
		String EX_SAVE_PAYMENT_DETAILS = "ex.save.payment.details";
		String ERR_UPDATE_BANK_DETAILS = "err_update_bank_details";
		String EX_UPDATE_BANK_DETAILS =  "ex_update_bank_details";
		String ERR_UPDATE_BANK_STATUS = "err_update_bank_status";
		String EX_UPDATE_BANK_STATUS = "ex_update_bank_status";
		String ERR_RETRIEVE_ALL_BANK_DETAILS = "err_retrive_all_bank_details";
		String EX_RETRIEVE_ALL_BANK_DETAILS = "ex_retrive_all_bank_details";
		String ERR_UPDATE_PAYMENT_DETAILS = "err.update.payment.details";
		String EX_UPDATE_PAYMENT_DETAILS = "ex.update.payment.details";
		String ERR_UPDATE_PAYMENT_STATUS = "err.update.payment.status";
		String EX_UPDATE_PAYMENT_STATUS = "ex.update.payment.status";
		String ERR_RETRIEVE_ALL_PAYMENT_DETAILS = "err.retrieve.all.payment.details";
		String EX_RETRIEVE_ALL_PAYMENT_DETAILS = "ex.retrieve.all.payment.details";
		String ERR_SAVE_PRODUCT_IMAGE_DETAILS = "err_save_productImage_details";
		String EX_SAVE_PRODUCT_IMAGE_DETAILS = "ex_save_productImage_details";
		String ERR_UPDATE_PRODUCT_IMAGE_DETAILS = "err_update_productImage_details";
		String EX_UPDATE_PRODUCT_IMAGE_DETAILS = "ex_update_productImage_details";
		String ERR_PRODUCT_IMAGE_STATUS = "err_update_productImage_status";
		String EX_PRODUCT_IMAGE_STATUS = "ex_update_productImage_status";
		String ERR_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS = "ex_retrive_all_productImage_details";
		String EX_RETRIEVE_ALL_PRODUCT_IMAGE_DETAILS = "ex_retrive_all_productImage_details";
        String ERR_RETRIEVE_ALL_PRODUCT_DETAILS_BY_SEARCH = "err.retrieve.all.product.details.by.search";
        String EX_RETRIEVE_ALL_PRODUCT_DETAILS_BY_SEARCH = "ex.retrieve.all.product.details.by.search";
		String ERR_RETRIEVE_ALL_FILTERED_PRODUCT_DETAILS = "err.retrieve.all.product.filter.details";
		String EX_RETRIEVE_ALL_FILTERED_PRODUCT_DETAILS = "ex.retrieve.all.product.filter.details";
		String ERR_EMAIL_VERIFICATION_TOKEN_SEND = "err.verification.token.email";
		String EX_EMAIL_VERIFICATION_TOKEN_SEND = "ex.verification.token.email";
		String ERR_TWO_STEP_SEND = "err.two.step.verification.mail.send";
		String EX_TWO_STEP_SEND = "ex.two.step.verification.mail.send";
		String ERR_TWO_STEP_VERIFICATION = "err.two.step.verification";
		String EX_TWO_STEP_VERIFICATION = "ex.two.step.verification";
		String ERR_EMAIL_VERIFICATION_FAILED = "err.email.verification";
		String EX_EMAIL_VERIFICATION = "ex.email.verification";
		String ERR_UPLOAD_AND_SAVE_PRODUCT_IMAGE = "err.upload.and.save.product.image";
		String EX_UPLOAD_AND_SAVE_PRODUCT_IMAGE = "ex.upload.and.save.product.image";
		String ERR_PRODUCT_IMAGE_PRODUCT_ID_REQUIRED = "err.product.image.product.id.required";
		String EX_PRODUCT_IMAGE_PRODUCT_ID_REQUIRED = "ex.product.image.product.id.required";
		String ERR_SAVE_PRODUCT_CATEGORY = "err.save.product.category";
		String EX_SAVE_PRODUCT_CATEGORY = "ex.save.product.category";
		String ERR_UPDATE_PRODUCT_CATEGORY = "err.update.product.category";
		String EX_UPDATE_PRODUCT_CATEGORY = "ex.update.product.category";
		String ERR_GET_PRODUCT_CATEGORY_BY_ID = "err.get.product.category.by.id";
		String EX_GET_PRODUCT_CATEGORY_BY_ID = "ex.get.product.category.by.id";
		String ERR_GET_ALL_PRODUCT_CATEGORY = "err.get.all.product.category";
		String EX_GET_ALL_PRODUCT_CATEGORY = "ex.get.all.product.category";
		String ERR_GET_ALL_PRODUCT_CATEGORY_PAGE = "err.get.all.product.category.page";
		String EX_GET_ALL_PRODUCT_CATEGORY_PAGE = "ex.get.all.product.category.page";
		String ERR_GET_PRODUCT_CATEGORY_TREE = "err.get.product.category.tree";
		String EX_GET_PRODUCT_CATEGORY_TREE = "ex.get.product.category.tree";
		String ERR_UPDATE_PRODUCT_CATEGORY_STATUS = "err.update.product.category.status";
		String EX_UPDATE_PRODUCT_CATEGORY_STATUS = "ex.update.product.category.status";
		String ERR_GET_ALL_PRODUCT_CATEGORY_PAGE_SEARCH = "err.get.all.product.category.page.search";
		String EX_GET_ALL_PRODUCT_CATEGORY_PAGE_SEARCH = "ex.get.all.product.category.page.search";
        String ERR_SAVE_FAVOURITE_DETAILS = "err.save.favourite.details";
        String EX_SAVE_FAVOURITE_DETAILS = "ex.save.favourite.details";
        String ERR_UPDATE_FAVOURITE_DETAILS = "err.update.favourite.details";
        String EX_UPDATE_FAVOURITE_DETAILS = "ex.update.favourite.details";
        String ERR_RETRIEVE_ALL_FAVOURITE_DETAILS = "err.retrieve.all.favourite.details";
        String EX_RETRIEVE_ALL_FAVOURITE_DETAILS = "ex.retrieve.all.favourite.details";
        String ERR_CHANGE_PASSWORD = "err.change.password";
        String EX_CHANGE_PASSWORD = "ex.change.password";
        String ERR_CHANGE_SAME_PASSWORD = "err.change.same.password";
        String EX_CHANGE_SAME_PASSWORD = "ex.change.same.password"; 
	}
}
