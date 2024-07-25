package com.example.demorequest.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demorequest.network.UserData

@Composable
fun DisplayScreen(
    fetchState: FetchVerdict,
    modifier: Modifier = Modifier
) {
    when (fetchState) {
        is FetchVerdict.Error -> MessageScreen(msg = fetchState.errorMsg)
        FetchVerdict.Loading -> MessageScreen(msg = "Loading...")
        is FetchVerdict.Success -> SuccessScreen(userDataList = fetchState.result)
    }
}

@Composable
fun SuccessScreen(
    userDataList: List<UserData>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(top = 20.dp)) {
        userDataList.forEachIndexed { index, user ->
            item {
                UserDetails(user, index)
                Spacer(Modifier.height(20.dp))
                HorizontalDivider(thickness = 2.dp)
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun UserDetails(
    user: UserData,
    idx: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "${idx + 1}. " + user.handle + (if (user.firstName != null) ", ${user.firstName} ${user.lastName}" else ""),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Current Rating: ${user.rating}\n Max. Rating: ${user.maxRating}",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Current Rank: ${user.rank}\n Max. Rank: ${user.maxRank}",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Contribution Score: ${user.contribution}",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Friend Of: ${user.friendOfCount} Users",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun MessageScreen(
    msg: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Status: $msg",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}