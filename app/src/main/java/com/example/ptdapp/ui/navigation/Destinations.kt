package com.example.ptdapp.ui.navigation

object Destinations {
    const val LOGIN_SCREEN = "login"
    const val REGISTER_SCREEN = "register"
    const val CREATE_GASTO_SCREEN = "create_gasto"
    const val CREATE_PTD_SCREEN = "create_ptd"
    const val DETAIL_GASTO_SCREEN = "detail_gasto/{gastoId}"
    const val DETAIL_PTD_SCREEN = "detail_ptd/{ptdId}"
    const val HOME_SCREEN = "home"
    const val NOTIFICATION_SCREEN = "notifications"
    const val PROFILE_SCREEN = "profile"
    const val WALLET_SCREEN = "wallet"
    const val MAIN_SCREEN = "main_screen"
    const val PAYMENT_SCREEN = "payment_screen"

    fun detailGastoRoute(gastoId: String) = "detail_gasto/$gastoId"
    fun detailPTDRoute(ptdId: String) = "detail_ptd/$ptdId"
}
