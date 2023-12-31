package com.example.gymprogress.feature.history_screen

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymprogress.R
import com.example.gymprogress.ui.common.SessionAdderDialog
import com.example.gymprogress.ui.model.toUiText
import com.example.gymprogress.util.PermissionsUtil.getTextToShowGivenPermissions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HistoryScreen(
    onListItemClick: (Int) -> Unit,
    onFabClick: () -> Unit,
    onStatClick: () -> Unit,
    viewModel: HistoryViewModel = viewModel(factory = HistoryViewModel.Factory),

    ){
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    var locationListener: LocationListener


    var showDialog by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    var locationManager: LocationManager =
    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager



    val locationPermission = rememberMultiplePermissionsState(permissions = listOf(android.Manifest.permission.ACCESS_COARSE_LOCATION))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.gym_tracker), fontSize = 20.sp, modifier = Modifier.size(70.dp) )
                              },
                actions = {
                    IconButton(onClick = { onStatClick() }) {
                        Icon(imageVector = Icons.Default.StarRate, contentDescription = null)

                    }
                }
          )
        },
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = {showDialog =true},
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(
                    color = if (state.isLoading) {
                        MaterialTheme.colorScheme.secondaryContainer
                    } else {
                        MaterialTheme.colorScheme.background
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            } else if (state.isError) {
                Text(
                    text = state.error?.toUiText()?.asString(context)
                        ?: stringResource(id = R.string.some_error_message)
                )
            } else {
                if (state.sessions.isEmpty()) {
                    Text(text = stringResource(id = R.string.text_empty_todo_list))
                } else {
                       Column{
                           LazyColumn(modifier = Modifier.fillMaxSize()){
                               items(state.sessions.size) { i ->
                                   ListItem(
                                       headlineText = {
                                           Row(verticalAlignment = Alignment.CenterVertically,
                                               modifier = Modifier.fillMaxWidth(),
                                           horizontalArrangement = Arrangement.SpaceBetween) {
                                               Column(modifier = Modifier.weight(1f)) {
                                                   Row() {
                                                       IconButton(
                                                           onClick = { },
                                                           enabled = !state.sessions[i].finished
                                                       ) {
                                                           if (!state.sessions[i].finished) {
                                                               Icon(
                                                                   imageVector = Icons.Default.ArrowRight,
                                                                   contentDescription = null,
                                                                   modifier = Modifier.size(40.dp),
                                                                   tint = Color.Green
                                                               )
                                                           } else {
                                                               Icon(
                                                                   imageVector = Icons.Default.HistoryToggleOff,
                                                                   contentDescription = null,
                                                                   modifier = Modifier.size(40.dp)
                                                               )

                                                           }
                                                       }
                                                       IconButton(onClick = {
                                                           val session = state.sessions[i]
                                                           val uriString = "geo:${session.latitude},${session.longitude}?z=10?q=${session.latitude},${session.longitude}(${session.name})"
                                                           val gmmIntentUri =
                                                               Uri.parse(uriString)
                                                           val mapIntent =
                                                               Intent(
                                                                   Intent.ACTION_VIEW,
                                                                   gmmIntentUri
                                                               )
                                                           mapIntent.setPackage("com.google.android.apps.maps")

                                                           startActivity(context, mapIntent, null)

                                                       }) {
                                                           Icon(
                                                               Icons.Default.Public,
                                                               contentDescription = null
                                                           )
                                                       }
                                                   }
                                               }
                                               Column(modifier = Modifier.weight(3f)) {
                                                   Row(
                                                       horizontalArrangement = Arrangement.SpaceAround,
                                                       verticalAlignment = Alignment.CenterVertically

                                                   ) {
                                                       Text(
                                                           text = state.sessions[i].name,
                                                           fontSize = 24.sp,
                                                           modifier = Modifier.padding(20.dp)
                                                       )

                                                   }
                                               }
                                               Column(modifier = Modifier.weight(1f)) {
                                                   IconButton(
                                                       onClick = {viewModel.deleteSession(state.sessions[i])},
                                                   ) {
                                                       Icon(
                                                           imageVector = Icons.Default.Delete,
                                                           contentDescription = null
                                                       )
                                                   }
                                               }
                                           }
                                       },
                                       modifier = Modifier.clickable(onClick = { onListItemClick(state.sessions[i].id) })
                                   )
                                   if (i != state.sessions.lastIndex) {
                                       Divider(
                                           thickness = 2.dp,
                                           color = MaterialTheme.colorScheme.secondaryContainer
                                       )
                                   }
                               }
                           }
                       }
                    }
                }
            }
        if(showDialog) {
            if (ContextCompat.checkSelfPermission( context,android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED )
            {
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, locationListener)

                SessionAdderDialog(onConfirm = {
                    viewModel.onSave(it)
                    showDialog = false
                },
                    onDismiss = { showDialog = false }
                )
            }
            else{
                showPermissionDialog = true
            }
        }
        if(showPermissionDialog){
            AlertDialog(
                onDismissRequest = { showPermissionDialog = false},
                confirmButton = {
                    Button(onClick = { locationPermission.launchMultiplePermissionRequest()
                                        showPermissionDialog = false}) {
                        Text(stringResource(id = R.string.button_label_request_permission))
                    }
                },
                text = {
                    Text(
                        getTextToShowGivenPermissions(
                            locationPermission.revokedPermissions,
                            locationPermission.shouldShowRationale,
                            context
                        )
                    )
            }
            )

        }
    }
}
