package com.koohyar.filterRecycle

import android.content.Context
import android.content.SharedPreferences

///best pref
class PrefsUtil (context: Context)
{
    companion object{
        const val MY_APP_MAIN_PASSWORD = "my_pass"
        const val BUBBLE_ICON_URI = "bubble_icon_uri"
        const val ACCEPTED_PRIVACY = "accepted_privacy"
        const val REMOVE_BUBBLE_SHORTCUT = "remove_bubble"
        const val BUBBLE_SIZE = "bubble_size"
        const val RANDOM_PASSWORD_LENGTH = "random_pass_length"
        const val RANDOM_PASSWORD_LOWERCASE = "random_pass_lowercase"
        const val RANDOM_PASSWORD_UPPERCASE = "random_pass_uppercase"
        const val RANDOM_PASSWORD_DIGITS = "random_pass_digits"
        const val RANDOM_PASSWORD_SPECIAL_CHARS = "random_pass_special_chars"
        const val ENABLE_TEXT_RECOGNITION_ANIMATION = "text_recognition_animation"
        const val LEARN_FLIP_BACK_CARD = "learn_flip_bank_card"
        const val LEARN_FLIP_PERSON_CARD = "learn_flip_person_card"
        const val CLEAR_CLIPBOARD_EVERY_TIME = "clear_clipboard_every_time"

    }

     private val preferences: SharedPreferences = context.getSharedPreferences("mypref",Context.MODE_PRIVATE)


    var clearClipboard:Boolean
        get(){
            return preferences.getBoolean(CLEAR_CLIPBOARD_EVERY_TIME, false)
        }
        set(value) {
            preferences.edit().putBoolean(CLEAR_CLIPBOARD_EVERY_TIME, value).apply()

        }


    var enableTextRecognitionAnimation:Boolean
        get(){
            return preferences.getBoolean(ENABLE_TEXT_RECOGNITION_ANIMATION, true)
        }
        set(value) {
            preferences.edit().putBoolean(ENABLE_TEXT_RECOGNITION_ANIMATION, value).apply()

        }

    var isLearnFlipBankCard:Boolean
        get(){
            return preferences.getBoolean(LEARN_FLIP_BACK_CARD, false)
        }
        set(value) {
            preferences.edit().putBoolean(LEARN_FLIP_BACK_CARD, value).apply()

        }

    var isLearnFlipPersonCard:Boolean
        get(){
            return preferences.getBoolean(LEARN_FLIP_PERSON_CARD, false)
        }
        set(value) {
            preferences.edit().putBoolean(LEARN_FLIP_PERSON_CARD, value).apply()

        }

    var isPrivacyAccepted:Boolean
        get(){
            return preferences.getBoolean(ACCEPTED_PRIVACY, false)
        }
        set(value) {
            preferences.edit().putBoolean(ACCEPTED_PRIVACY, value).apply()

        }

    var removeBubbleShortcut:Boolean
        get(){
            return preferences.getBoolean(REMOVE_BUBBLE_SHORTCUT, false)
        }
        set(value) {
            preferences.edit().putBoolean(REMOVE_BUBBLE_SHORTCUT, value).apply()

        }

    var myPass:String
        get(){
            return preferences.getString(MY_APP_MAIN_PASSWORD, "")!!
        }
        set(value) {
            preferences.edit().putString(MY_APP_MAIN_PASSWORD, value).apply()

        }

    var bubbleSize:Int
        get(){
            return preferences.getInt(BUBBLE_SIZE, 1)
        }
        set(value) {
            preferences.edit().putInt(BUBBLE_SIZE, value).apply()

        }

    var bubbleIconUri:String
        get(){
            return preferences.getString(BUBBLE_ICON_URI, "")!!
        }
        set(value) {
            preferences.edit().putString(BUBBLE_ICON_URI, value).apply()

        }

    var enableLowercaseRandomPassword:Boolean
        get(){
            return preferences.getBoolean(RANDOM_PASSWORD_LOWERCASE, true)
        }
        set(value) {
            preferences.edit().putBoolean(RANDOM_PASSWORD_LOWERCASE, value).apply()

        }

    var enableUppercaseRandomPassword:Boolean
        get(){
            return preferences.getBoolean(RANDOM_PASSWORD_UPPERCASE, true)
        }
        set(value) {
            preferences.edit().putBoolean(RANDOM_PASSWORD_UPPERCASE, value).apply()

        }
    var enableDigitsRandomPassword:Boolean
        get(){
            return preferences.getBoolean(RANDOM_PASSWORD_DIGITS, true)
        }
        set(value) {
            preferences.edit().putBoolean(RANDOM_PASSWORD_DIGITS, value).apply()

        }
    var enableSpecialCharsRandomPassword:Boolean
        get(){
            return preferences.getBoolean(RANDOM_PASSWORD_SPECIAL_CHARS, true)
        }
        set(value) {
            preferences.edit().putBoolean(RANDOM_PASSWORD_SPECIAL_CHARS, value).apply()

        }


    var randomPasswordLength:Int
        get(){
            return preferences.getInt(RANDOM_PASSWORD_LENGTH, 8)
        }
        set(value) {
            preferences.edit().putInt(RANDOM_PASSWORD_LENGTH, value).apply()

        }

}