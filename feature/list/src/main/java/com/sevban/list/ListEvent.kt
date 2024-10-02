package com.sevban.list

sealed class ListEvent {
    data class OnItemClick(val id: Int) : ListEvent()
}
