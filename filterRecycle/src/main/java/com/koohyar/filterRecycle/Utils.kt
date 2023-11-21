package com.koohyar.filterRecycle

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.*
import android.os.*
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt


object Utils {

     fun View.addRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
        setBackgroundResource(resourceId)
    }
     fun View.removeRipple() = with(TypedValue()) {
        //context.theme.resolveAttribute(android.R.attr.trab, this, true)
        setBackgroundColor(Color.TRANSPARENT)
    }
    private fun View.addCircleRipple() = with(TypedValue()) {
        context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
        setBackgroundResource(resourceId)
    }
    fun View.rippleThenRemoveRipple() = run {
        this.addRipple()
        this.isClickable = true
        this.touched()

        Handler(Looper.getMainLooper()).postDelayed({
           this.isClickable = false
           this.removeRipple()
        },1000)
    }
    fun View.touched() = run {

        var originalDownTime: Long = SystemClock.uptimeMillis()
        var eventTime: Long = SystemClock.uptimeMillis() + 100
        this.dispatchTouchEvent(
            MotionEvent.obtain(
                originalDownTime,
                eventTime,
                MotionEvent.ACTION_DOWN,
                100F,
                0F,
                0
            )
        )
        this.dispatchTouchEvent(
            MotionEvent.obtain(
                originalDownTime,
                eventTime,
                MotionEvent.ACTION_UP ,
                100F,
                0F,
                0
            )
        )
    }
     fun Context?.getLifeCycleOwner() : AppCompatActivity? = when (this) {
        is ContextWrapper -> if (this is AppCompatActivity) this else this.baseContext.getLifeCycleOwner()
        else -> null
    }
    fun getHexFromIntColor(color: Int?): String {
        return if (color != null)
            java.lang.String.format("#%06X", 0xFFFFFF and color)
        else "NO COLOR!!!!!!!"
    }






    private val persianNumbers = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")

    fun getColor(context: Context, id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(id, null)
        } else {
            context.resources.getColor(id)
        }
    }

    @ColorInt
    fun getAttrColor(context: Context, attr: Int): Int {
        val typedValue = TypedValue()
        val theme = context.theme
        theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }

    @ColorInt
    fun setAlphaColor( color: Int , alpha:Int): Int {
        return Color.argb(
            alpha,
            Color.red(color),
            Color.green(color),
            Color.blue(color)
        )
    }

    fun toPersianNumber(text: String): String? {
        if (text.isEmpty()) return ""
        var out: String? = ""
        val length = text.length
        for (i in 0 until length) {
            when (val c = text[i]) {
                in '0'..'9' -> {
                    val number = c.toString().toInt()
                    out += persianNumbers[number]
                }
                '٫' -> {
                    out += '،'
                }
                else -> {
                    out += c
                }
            }
        }
        return out
    }


    /*    val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                // prefs.isHomePressedPerson=true

                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Log.d(AddPersonActivity.TAG, "onResult : granted")
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Log.d(AddPersonActivity.TAG, "onResult : notGranted")

                }
            }*/
    fun onRequestPermission(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                    //startImagePicker(requestForImageLauncher)


                }

                // shouldShowRequestPermissionRationale(context) -> {

                //Log.d(TAG, "onRequest : rationale")
                // requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE)

                //  }
                else -> {
                    // Log.d(TAG, "onRequest : notGranted")
                    // requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE)

                }
            }
        } else {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    READ_EXTERNAL_STORAGE
                ) -> {
                    //  granted


                }
                else -> {
                    //notGranted
                    //  requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE)

                }
            }
        }
    }

    private fun convertDpToPx(context: Context, dp: Int): Int {
        return (dp * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
    fun Int.convertToDP(px: Float,context: Context):Float= px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    fun Int.convertToPX(context: Context):Int= (this * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    //var onItemClick: ((Int) -> Unit)? = null
    private val openRecycleViewSlideMenu: ((Int) -> Unit) = { position ->
        Log.d("openRecycleViewSlide", "opened: $position")
    }

    private fun setUpItemTouchHelper(context: Context) {

        val simpleCallback: ItemTouchHelper.SimpleCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {

                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                        viewHolder.itemView.translationX = dX / 2

                        val itemView = viewHolder.itemView
                        val p = Paint()
                        if (dX > 0) {
                            /* Set your color for positive displacement *//*


                            p.color=Color.RED
                            // Draw Rect with varying right side, equal to displacement dX
                            c.drawRect(
                                itemView.left.toFloat(),
                                itemView.top.toFloat()+convertDpToPx(16),
                                dX,
                                itemView.bottom.toFloat()-convertDpToPx(16),
                                p
                            )
                            p.setARGB(255, 255, 255, 255)
                            p.textSize=75F
                            c.drawText("حذف کردن",
                                itemView.left.toFloat() + convertDpToPx(16),
                                itemView.getTop().toFloat() + (itemView.getBottom().toFloat() - itemView.getTop().toFloat())/2 + p.textSize/3,
                                p)
                        }
                        else if(dX < 0) {

                            *//* Set your color for positive displacement */
                            p.color = Color.GREEN
                            // Draw Rect with varying right side, equal to displacement dX
                            c.drawRect(
                                itemView.right.toFloat(),
                                itemView.top.toFloat() + convertDpToPx(context, 16),
                                dX,
                                itemView.bottom.toFloat() - convertDpToPx(context, 16),
                                p
                            )
                            p.setARGB(255, 0, 0, 0)
                            p.textSize = 75F
                            c.drawText(
                                "تغییر دادن",
                                itemView.right.toFloat() - convertDpToPx(context, 128),
                                itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat()) / 2 + p.textSize / 3,
                                p
                            )
                            //swipe left
                        }

                    } else {
                        super.onChildDraw(
                            c,
                            recyclerView,
                            viewHolder,
                            dX,
                            dY,
                            actionState,
                            isCurrentlyActive
                        )
                    }


                }

                override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {

                    return 1.0F
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    when (direction) {
                        8 -> {
                            // swipe left to right // do a work

                        }
                        4 -> {

                            // swipe right to left // do a work


                        }
                        else -> {}
                    }


                }
            }


        //val itemTouchHelper = ItemTouchHelper(simpleCallback)
        // itemTouchHelper.attachToRecyclerView(binding.cardDetailsrecyclerview)
    }


    fun Activity.closeKeyboard() = run {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    fun Activity.openKeyboard() = run {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager!!.showSoftInput(currentFocus, 0)
    }

    fun openKeyBoardWithTouchView(view: View) {
        view.dispatchTouchEvent(
            MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN,
                0f,
                0f,
                0
            )
        )
        view.dispatchTouchEvent(
            MotionEvent.obtain(
                SystemClock.uptimeMillis(),
                SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP,
                0f,
                0f,
                0
            )
        )
    }

}