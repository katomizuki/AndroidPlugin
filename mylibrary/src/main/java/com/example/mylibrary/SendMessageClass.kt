package com.example.mylibrary
import com.unity3d.player.UnityPlayer

class SendMessageClass {
    public fun SendMessage(gameObject: String, method: String, message: String) {
        UnityPlayer.UnitySendMessage(gameObject, method, message)
    }
}