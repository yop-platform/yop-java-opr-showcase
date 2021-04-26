DROP TABLE IF EXISTS `YD_INDUSTRY_SOLUTION`;
CREATE TABLE YD_INDUSTRY_SOLUTION(ID INT PRIMARY KEY AUTO_INCREMENT, INDUSTRY_CODE VARCHAR(64) NOT NULL, INDUSTRY_NAME
VARCHAR(128) NOT NULL);

DROP TABLE IF EXISTS `YD_PRODUCT`;
CREATE TABLE YD_PRODUCT(ID INT PRIMARY KEY AUTO_INCREMENT, PRODUCT_CODE VARCHAR(64) NOT NULL, PRODUCT_NAME  VARCHAR
(128) NOT NULL, INDUSTRY_ID INT NOT NULL);

DROP TABLE IF EXISTS `YD_BIZ_STEP`;
CREATE TABLE YD_BIZ_STEP(ID INT PRIMARY KEY AUTO_INCREMENT, STEP_URL VARCHAR(64) NOT NULL, PRODUCT_NAME  VARCHAR
(128) NOT NULL,REQUEST_TYPE VARCHAR(64) NOT NULL, INDUSTRY_ID INT NOT NULL,NEXT_STEP_ID INT);

--支付表
DROP TABLE IF EXISTS `YD_PAY_ORDER`;
CREATE TABLE YD_PAY_ORDER (ID INT PRIMARY KEY AUTO_INCREMENT,
MERCHANT_NO VARCHAR(32) NOT NULL,
ORDER_ID VARCHAR(64) NOT NULL,
ORDER_AMOUNT DECIMAL(12,2) NOT NULL,
GOODS_NAME VARCHAR(128),
FUND_PROCESS_TYPE VARCHAR(32),
NOTIFY_URL VARCHAR(200) NOT NULL,
MEMO VARCHAR(32),
EXPIRED_TIME TIMESTAMP,
REDIRECT_URL VARCHAR(200) NOT NULL,
ERROR_CODE VARCHAR(32),
ERROR_MSG VARCHAR(128),
BIZ_SYSTEM_NO VARCHAR(32),
UNIQUE_ORDER_NO VARCHAR(64),
TOKEN VARCHAR(128),
STATUS VARCHAR(32),
PAY_SUCCESS_DATE TIMESTAMP,
PAY_WAY VARCHAR(32),
CHANNEL VARCHAR(32),
CARD_TYPE VARCHAR(16),
BANK_ID VARCHAR(16),
BANK_CARD_NO VARCHAR(32),
MOBILE_PHONE_NO VARCHAR(16),
USER_ID VARCHAR(64),
UNION_ID VARCHAR(64),
PRODUCT_ORDER_ID VARCHAR(64),
CREATE_TIME TIMESTAMP NOT NULL,
LAST_UPDATE_TIME TIMESTAMP NOT NULL,
BANK_ORDER_ID VARCHAR(64),
MERCHANT_FEE DECIMAL(12,2),
CUSTOMER_FEE DECIMAL(12,2));

--退款表
DROP TABLE IF EXISTS `YD_REFUND_ORDER`;
CREATE TABLE YD_REFUND_ORDER (ID INT PRIMARY KEY AUTO_INCREMENT,
MERCHANT_NO VARCHAR(32) NOT NULL,
PAY_ORDER_ID VARCHAR(64) NOT NULL,
REFUND_REQUEST_ID VARCHAR(64) NOT NULL,
UNIQUE_REFUND_NO VARCHAR(64),
PAY_UNIQUE_ORDER_NO VARCHAR(64) NOT NULL,
REFUND_AMOUNT DECIMAL(12,2) NOT NULL,
REAL_REFUND_AMOUNT DECIMAL(12,2),
DESCRIPTION VARCHAR(128),
MEMO VARCHAR(64),
REFUND_ACCOUNT_TYPE VARCHAR(32),
NOTIFY_URL VARCHAR(200) NOT NULL,
ERROR_CODE VARCHAR(32),
ERROR_MSG VARCHAR(128),
STATUS VARCHAR(32),
REFUND_SUCCESS_DATE TIMESTAMP,
CREATE_TIME TIMESTAMP NOT NULL,
LAST_UPDATE_TIME TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_areaId` char(6)  NOT NULL,
  `address_name` varchar(50) NOT NULL,
  `address_regionId` char(6) NOT NULL
);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(10) NOT NULL,
  `category_name` varchar(20) NOT NULL,
  `category_image_src` varchar(255) NOT NULL
);

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(10) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `product_title` varchar(100) NULL DEFAULT NULL,
  `product_price` decimal(10, 2) NULL DEFAULT NULL,
  `product_sale_price` decimal(10, 2) NOT NULL,
  `product_create_date` datetime(0) NOT NULL,
  `product_category_id` int(10) NOT NULL,
  `product_isEnabled` tinyint(1) NOT NULL DEFAULT 0,
  `product_sale_count` int(10) NOT NULL DEFAULT 0,
  `product_review_count` int(10) NOT NULL DEFAULT 0
);

DROP TABLE IF EXISTS `productimage`;
CREATE TABLE `productimage`  (
  `productimage_id` int(10) NOT NULL AUTO_INCREMENT,
  `productimage_type` tinyint(1)  NOT NULL,
  `productimage_src` varchar(255)  NOT NULL,
  `productimage_product_id` int(10) NOT NULL
);

DROP TABLE IF EXISTS `productorder`;
CREATE TABLE `productorder`  (
  `productorder_id` int(10) NOT NULL AUTO_INCREMENT,
  `productorder_code` varchar(30)  NOT NULL,
  `productorder_address` char(6) NOT NULL,
  `productorder_detail_address` varchar(255)NOT NULL,
  `productorder_post` char(6) NULL DEFAULT NULL,
  `productorder_receiver` varchar(20) NOT NULL,
  `productorder_mobile` char(11) NOT NULL,
  `productorder_pay_date` datetime(0) NOT NULL,
  `productorder_delivery_date` datetime(0) NULL DEFAULT NULL,
  `productorder_confirm_date` datetime(0) NULL DEFAULT NULL,
  `productorder_status` tinyint(1) NOT NULL,
  `productorder_user_id` int(10) NOT NULL
);

DROP TABLE IF EXISTS `productorderitem`;
CREATE TABLE `productorderitem`  (
  `productorderitem_id` int(10) NOT NULL AUTO_INCREMENT,
  `productorderitem_number` smallint(5) NOT NULL,
  `productorderitem_price` decimal(10, 2) NOT NULL,
  `productorderitem_product_id` int(10) NOT NULL,
  `productorderitem_order_id` int(10) NULL DEFAULT NULL,
  `productorderitem_user_id` int(10) NOT NULL,
  `productorderitem_userMessage` varchar(255) NULL DEFAULT NULL
);

DROP TABLE IF EXISTS `property`;
CREATE TABLE `property`  (
  `property_id` int(10) NOT NULL AUTO_INCREMENT,
  `property_name` varchar(25) NOT NULL,
  `property_category_id` int(10) NOT NULL
);

DROP TABLE IF EXISTS `propertyvalue`;
CREATE TABLE `propertyvalue`  (
  `propertyvalue_id` int(10) NOT NULL AUTO_INCREMENT,
  `propertyvalue_value` varchar(100) NOT NULL,
  `propertyvalue_property_id` int(10) NOT NULL,
  `propertyvalue_product_id` int(10) NOT NULL
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `user_nickname` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_realname` varchar(20) NULL DEFAULT NULL,
  `user_gender` tinyint(1) NOT NULL,
  `user_birthday` date NOT NULL,
  `user_address` char(6)  NOT NULL,
  `user_homeplace` char(6)  NOT NULL,
  `user_profile_picture_src`  NULL DEFAULT NULL
);

