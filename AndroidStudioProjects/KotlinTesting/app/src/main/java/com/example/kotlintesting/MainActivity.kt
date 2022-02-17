package com.example.kotlintesting

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentDateTime = Calendar.getInstance()
        val dateNow = Calendar.getInstance().time

        val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val today: String = formatter.format(dateNow)

        val formatter2: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val today2: String = formatter2.format(dateNow)


        val randomString = getSqlDate("January12021")
        val formatter3: DateFormat = SimpleDateFormat("EEE, d MMM yyyy")

       val myDate :String = getCalculatedDate("", "yyyy-MM-dd", -7) // If you want date from today

        val formatString: String = getSqlDate("April312221")


        //text 3
        val stamp = Timestamp(1619198899*1000L)
        val date = Date(stamp.time)
        val formatter4: DateFormat = SimpleDateFormat("EEEE, MMM d")
        val quickDate: String = formatter4.format(date)

        val stamp2 = Timestamp(System.currentTimeMillis()*1000L)
        val date2 = Date(stamp2.time)
        val time = java.lang.System.currentTimeMillis()

        val oneWeekMillis: Long = 604800000
        val currentTimeMillis: Long = System.currentTimeMillis()

        val twelveWeekDateArray: ArrayList<Long> = ArrayList()
        for (i in 0..11) {
            Log.d(TAG, "onCreate: 1")
            twelveWeekDateArray.add(currentTimeMillis - (i*oneWeekMillis))
        }

        val oneWeekSecs: Int = 604800000/1000
        val currentTimeMillisConvertedToSeconds: Long = System.currentTimeMillis()/1000
        val currentTimeSecs: Int = currentTimeMillisConvertedToSeconds.toInt()

        val twelveWeekDateArray2: ArrayList<Int> = ArrayList()
        for (i in 0..11) {
            Log.d(TAG, "onCreate: 1")
            twelveWeekDateArray2.add(currentTimeSecs - (i*oneWeekSecs))
        }

        //text25
        //hh:mm:ss format
        val secs = 20000.82 // should equal 1 hour and 1
        val time25 = String.format("%02d:%02d:%02d", (secs / 3600.0).toInt(), ((secs % 3600.0) / 60.0).toInt(), (secs % 60.0).toInt())
        val time2552 = String.format("%02d:%02d.%02d", (secs / 60.0).toInt(), (secs % 60.0).toInt(), (((secs % 60.0)*100.0) % 100.0).roundToInt())


        val secs2 = 5525.60
//        val time10 = (secs2 / 3600.0)
//        "%.4f".format(num)
        val time10 = String.format("%.1f", secs2/3600.0)







        setText.setOnClickListener {
            text1.text = dateNow.toString()
            text2.text =myDate
            text3.text = quickDate
            text4.text = today2
            text5.text = time2552
            text25.text = time25
            text2552.text = time10

            for (i in 0..11) {
                text6.append(convertLongToDate(twelveWeekDateArray[i]).toString() + "\n")
            }
            for (i in 0..11) {
                text7.append(twelveWeekDateArray[i].toString() + "\n")
            }
            for (i in 0..11) {
                text8.append(twelveWeekDateArray2[i].toString() + "\n")
            }

            println("Today : $today")
        }


        button_random_number.setOnClickListener{
            random_number.text = RandomIdGenerator.generateUniqueId().toString()
        }

    }

    fun convertLongToDate(time: Long) : Date {
        try {
            val stamp = Timestamp(time)
            return Date(stamp.time)
        } catch (e: java.lang.Exception) {
            throw java.lang.Exception(e)
        }
    }

    private fun getSqlDate(date: String): String {
        val stringWithOnlyDigits = date.filter { it.isDigit() }
        val stringWithOnlyLetters = date.filter { it.isLetter() }

        var monthString = "25"
        when (stringWithOnlyLetters) {
            "January" -> monthString = "01"
            "February" -> monthString = "02"
            "March" -> monthString = "03"
            "April" -> monthString = "04"
            "May" -> monthString = "05"
            "June" -> monthString = "06"
            "July" -> monthString = "07"
            "August" -> monthString = "08"
            "September" -> monthString = "09"
            "October" -> monthString = "10"
            "November" -> monthString = "11"
            "December" -> monthString = "12"
        }

        val yearString: String = stringWithOnlyDigits.takeLast(4)

        var dayString: String = if(stringWithOnlyDigits.length == 5) {
            stringWithOnlyDigits.take(1)
        } else {
            stringWithOnlyDigits.take(2)
        }

        return "$yearString-$monthString-$dayString"
    }


//    arraylist
//    for (i 1...12) {
//        arrayList[i] = getCalculatedDate("", "yyyy-MM-dd", (-7*i))
//    }
    private fun getCalculatedDate(date: String, dateFormat: String, days: Int): String {
        val cal = Calendar.getInstance()
        val s = SimpleDateFormat(dateFormat)
        if (date.isNotEmpty()) {
            cal.time = s.parse(date)
        }
        cal.add(Calendar.DAY_OF_YEAR, days)
        return s.format(Date(cal.timeInMillis))


    }



//    private fun getFormattedDate(dateNow: Date) : String {
////        val current: String = dateNow.toString()
////        current.find { "" }
////
////
////        return current
//    }


}