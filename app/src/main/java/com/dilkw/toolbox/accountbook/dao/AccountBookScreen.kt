package com.dilkw.toolbox.accountbook.dao

import androidx.annotation.StringRes
import com.dilkw.toolbox.R

sealed class AccountBookScreen(val route: String, @StringRes val resourceId: Int) {
    object Statistics : AccountBookScreen("accountstatistics", R.string.account_statistics)
    object AccountList : AccountBookScreen("accountlist", R.string.account_list)
}
