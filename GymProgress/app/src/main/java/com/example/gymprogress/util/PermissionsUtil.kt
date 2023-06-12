package com.example.gymprogress.util

import android.content.Context
import com.example.gymprogress.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

object PermissionsUtil {
    @ExperimentalPermissionsApi
    fun getTextToShowGivenPermissions(
        permissions: List<PermissionState>,
        shouldShowRationale: Boolean,
        context: Context
    ): String {
        val revokedPermissionsSize = permissions.size
        if (revokedPermissionsSize == 0) return ""
        val textToShow = StringBuilder()

        with(context) {
            textToShow.apply {
                append(getString(R.string.permission_grant_ask_beginning))
            }

            for (i in permissions.indices) {
                val permission = permissions[i].permission
                    .replace(Regex("[a-z]|[.]"), "")
                textToShow.append(" $permission")
                when {
                    revokedPermissionsSize > 1 && i == revokedPermissionsSize - 2 -> {
                        textToShow.append(getString(R.string.permission_grant_ask_separator_before_last_permission))
                    }
                    else -> {
                        textToShow.append(getString(R.string.permission_grant_ask_separator))
                    }
                }
            }
            textToShow.append(
                if (revokedPermissionsSize == 1) {
                    getString(R.string.permission_grant_ask_singular_permission)
                } else getString(R.string.permission_grant_ask_multiple_permissions)
            )
            textToShow.append(
                if (shouldShowRationale) {
                    getString(R.string.permission_grant_ask_rationale)
                } else {
                    getString(R.string.permission_grant_ask_denied)
                }
            )
        }

        return textToShow.toString()
    }
}