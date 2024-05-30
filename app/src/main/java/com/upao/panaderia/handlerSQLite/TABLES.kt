package com.upao.panaderia.handlerSQLite

class TABLES {

    // Tabla de Usuarios
    val TABLE_USER = "usuarios"
    val USER_ID = "idUsuario"
    val USER_NAME = "nombre"
    val USER_LASTNAME = "apellido"
    val USER_EMAIL = "email"
    val USER_PASSWORD = "password"
    val USER_ROLE = "rol"
    val USER_ISACTIVE = "isActive"
    val USER_ISDELETE = "isDelete"
    val USER_CREATEDAT = "createdAT"
    val USER_UPDATEDAT = "updatedAT"

    val CREATE_TABLE_USER = ("CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " TEXT,"
            + USER_LASTNAME + " TEXT,"
            + USER_EMAIL + " TEXT,"
            + USER_PASSWORD + " TEXT,"
            + USER_ROLE + " TEXT,"
            + USER_ISACTIVE + " INTEGER,"
            + USER_ISDELETE + " INTEGER,"
            + USER_CREATEDAT + " TEXT,"
            + USER_UPDATEDAT + " TEXT"
            + ")")

    // Tabla de Productos
    val TABLE_PRODUCT = "productos"
    val PRODUCT_ID = "idProducto"
    val PRODUCT_NAME = "nombre"
    val PRODUCT_DESCRIPTION = "descripcion"
    val PRODUCT_PRICE = "precio"
    val PRODUCT_IMAGE = "imagen"
    val PRODUCT_STATUS = "estado"
    val PRODUCT_CATEGORY = "IdCategoria"
    val PRODUCT_ISACTIVE = "isActive"
    val PRODUCT_ISDELETE = "isDelete"
    val PRODUCT_CREATEDAT = "createdAT"
    val PRODUCT_UPDATEDAT = "updated"

    val CREATE_TABLE_PRODUCT = ("CREATE TABLE " + TABLE_PRODUCT + "("
            + PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + PRODUCT_NAME + " TEXT,"
            + PRODUCT_DESCRIPTION + " TEXT,"
            + PRODUCT_PRICE + " REAL,"
            + PRODUCT_IMAGE + " TEXT,"
            + PRODUCT_STATUS + " INTEGER,"
            + PRODUCT_CATEGORY + " INTEGER,"
            + PRODUCT_ISACTIVE + " INTEGER,"
            + PRODUCT_ISDELETE + " INTEGER,"
            + PRODUCT_CREATEDAT + " TEXT,"
            + PRODUCT_UPDATEDAT + " TEXT"
            + ")")

    // Tabla de Categorias
    val TABLE_CATEGORY = "categorias"
    val CATEGORY_ID = "idCategoria"
    val CATEGORY_NAME = "nombre"
    val CATEGORY_STATUS = "estado"
    val CATEGORY_ISACTIVE = "isActive"
    val CATEGORY_ISDELETE = "isDelete"
    val CATEGORY_CREATEDAT = "createdAT"
    val CATEGORY_UPDATEDAT = "updatedAT"

    val CREATE_TABLE_CATEGORY = ("CREATE TABLE " + TABLE_CATEGORY + "("
            + CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + CATEGORY_NAME + " TEXT,"
            + CATEGORY_STATUS + " INTEGER,"
            + CATEGORY_ISACTIVE + " INTEGER,"
            + CATEGORY_ISDELETE + " INTEGER,"
            + CATEGORY_CREATEDAT + " TEXT,"
            + CATEGORY_UPDATEDAT + " TEXT"
            + ")")

    // Tabla de Orders
    val TABLE_ORDER = "orders"
    val ORDER_ID = "idOrder"
    val ORDER_ID_USER = "idUser"
    val ORDER_ORDER_AMOUNT = "orderAmount"
    val ORDER_ORDER_STATUS = "orderStatus"
    val ORDER_PAYMENT_METHOD = "paymentMethod"
    val ORDER_PAYMENT_STATUS = "paymentStatus"
    val ORDER_CREATEDAT = "createdAT"
    val ORDER_UPDATEDAT = "updatedAT"

    val CREATE_TABLE_ORDER = ("CREATE TABLE " + TABLE_ORDER + "("
            + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ORDER_ID_USER + " INTEGER,"
            + ORDER_ORDER_AMOUNT + " REAL,"
            + ORDER_ORDER_STATUS + " TEXT,"
            + ORDER_PAYMENT_METHOD + " TEXT,"
            + ORDER_PAYMENT_STATUS + " TEXT,"
            + ORDER_CREATEDAT + " TEXT,"
            + ORDER_UPDATEDAT + " TEXT,"
            + "FOREIGN KEY (" + ORDER_ID_USER + ") REFERENCES " + TABLE_USER + "(" + USER_ID + ")"
            + ")")

    // Tabla de OrderDetails
    val TABLE_ORDERDETAILS = "orderDetails"
    val ORDERDETAILS_ID = "idOrderDetails"
    val ORDERDETAILS_ID_ORDER = "idOrder"
    val ORDERDETAILS_ID_PRODUCT = "idProduct"
    val ORDERDETAILS_QUANTITY = "quantity"
    val ORDERDETAILS_PRICE = "price"

    val CREATE_TABLE_ORDERDETAILS = ("CREATE TABLE " + TABLE_ORDERDETAILS + "("
            + ORDERDETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + ORDERDETAILS_ID_ORDER + " INTEGER,"
            + ORDERDETAILS_ID_PRODUCT + " INTEGER,"
            + ORDERDETAILS_QUANTITY + " INTEGER,"
            + ORDERDETAILS_PRICE + " REAL,"
            + "FOREIGN KEY (" + ORDERDETAILS_ID_ORDER + ") REFERENCES " + TABLE_ORDER + "(" + ORDER_ID + "),"
            + "FOREIGN KEY (" + ORDERDETAILS_ID_PRODUCT + ") REFERENCES " + TABLE_PRODUCT + "(" + PRODUCT_ID + ")"
            + ")")

    // Tabla de Puntos Fidelidad
    val TABLE_POINTS = "puntos_fidelidad"
    val POINTS_ID = "idPuntos"
    val POINTS_ID_USER = "idUser"
    val POINTS_POINTS = "puntos"
    val POINTS_CREATEDAT = "createdAT"
    val POINTS_UPDATEDAT = "updatedAT"

    val CREATE_TABLE_POINTS = ("CREATE TABLE " + TABLE_POINTS + "("
            + POINTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + POINTS_ID_USER + " INTEGER,"
            + POINTS_POINTS + " INTEGER,"
            + POINTS_CREATEDAT + " TEXT,"
            + POINTS_UPDATEDAT + " TEXT,"
            + "FOREIGN KEY (" + POINTS_ID_USER + ") REFERENCES " + TABLE_USER + "(" + USER_ID + ")"
            + ")")
}