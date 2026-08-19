package com.sheikhnaim.androidapp2.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sheikhnaim.androidapp2.data.OrderViewModel
import kotlinx.coroutines.launch

/**
 * Main application container managing full-screen horizontal paging between
 * the Welcome screen and each team member's order screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainerScreen(
    viewModel: OrderViewModel = viewModel()
) {
    // 1 welcome page + 4 team member pages = 5 total pages
    val totalPages = 1 + viewModel.teamMembers.size
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { totalPages })
    val coroutineScope = rememberCoroutineScope()

    var showHistorySheet by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        if (page == 0) {
            // Tab 0: Welcome Screen
            WelcomeScreen(
                onGetStartedClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(1) }
                },
                onHistoryClick = { showHistorySheet = true }
            )
        } else {
            // Tabs 1..4: Team Member Order Screens
            val memberIndex = page - 1
            val isLastMember = memberIndex == viewModel.teamMembers.size - 1

            OrderScreen(
                memberIndex = memberIndex,
                viewModel = viewModel,
                currentPage = page,
                onPageSelected = { targetPage ->
                    coroutineScope.launch { pagerState.animateScrollToPage(targetPage) }
                },
                onHomeClick = {
                    coroutineScope.launch { pagerState.animateScrollToPage(0) }
                },
                onHistoryClick = { showHistorySheet = true },
                onOrderFinished = {
                    if (isLastMember) {
                        showSuccessDialog = true
                    } else {
                        coroutineScope.launch { pagerState.animateScrollToPage(page + 1) }
                    }
                }
            )
        }
    }

    // Modal Bottom Sheet for History
    if (showHistorySheet) {
        ModalBottomSheet(
            onDismissRequest = { showHistorySheet = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            HistoryScreen(
                viewModel = viewModel,
                onDismiss = { showHistorySheet = false }
            )
        }
    }

    // Fullscreen Dialog for Success Celebration
    if (showSuccessDialog) {
        Dialog(
            onDismissRequest = { showSuccessDialog = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            SuccessScreen(
                onBackToWelcome = {
                    showSuccessDialog = false
                    coroutineScope.launch { pagerState.scrollToPage(0) }
                }
            )
        }
    }
}
