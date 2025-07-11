//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    //getting today's date, need the imports for this
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")
    println("🌙 Welcome to HairMoon – Lunar Haircut Advisor")
    println("Today is: ${today.format(formatter)}")

    //calling function and assigning it to an ummutable variable
    val moonPhase = getMoonPhase(today)
    println("Moon Phase: $moonPhase")

    //condition when waxing moon it's time for haircut
    if (moonPhase == "Waxing Moon") {
        println("✅ It's a great time to cut your hair! 💇‍♀️")
    } else {
        println("🚫 Not the ideal phase to cut hair. Maybe wait a few days.")
    }
    // Calling nearbySalons function.
    showNearbySalons()

    //starting user interaction with date input
    println("\nWould you like to check a future date? (Enter date YYYY-MM-DD(2025-05-02) or 'no' to exit and see next waxing moon date)")
    while (true) {
        print("> ")
        val input = readLine()// this reads user input
        //condition to see if it ends the user interaction
        if (input == null || input.lowercase() == "no") {
            println("👋 Goodbye and may your hair grow strong!")
            break
        }
        try {
            val userDate = LocalDate.parse(input)
            val phase = getMoonPhase(userDate)
            println("📅 ${userDate.format(formatter)} – Moon Phase: $phase")
            if (phase == "Waxing Moon") {
                println("✅ Yes! It's a waxing moon — perfect for a haircut!")
            } else {
                println("🚫 Not quite the right phase. Try a different date.")
            }
        } catch (e: Exception) {
            println("⚠️ Invalid date format. Please use YYYY-MM-DD or type 'no' to quit and see next waxing moon date.")
        }
    }

    // Show the next waxing moon
    val nextWaxingMoon = findNextWaxingMoon(today)
    println("\n🔮 The next Waxing Moon is on: ${nextWaxingMoon.format(formatter)}")
}

fun getMoonPhase(date: LocalDate): String {
    // This will simulate moon phases using a 29-day cycle
    val baseDate = LocalDate.of(2025, 1, 1) //my starting point
    val daysSince = java.time.temporal.ChronoUnit.DAYS.between(baseDate, date)
    val phaseDay = (daysSince % 29).toInt()

    // moon cycle
    return when (phaseDay) {
        in 0..6 -> "New Moon"
        in 7..13 -> "Waxing Moon"          // 🌙 Luna creciente
        in 14..20 -> "Full Moon"
        in 21..28 -> "Waning Moon"
        else -> "Unknown"
    }
}

// finding nex waxing day to inform before app closes
fun findNextWaxingMoon(startDate: LocalDate): LocalDate {
    var date = startDate.plusDays(1)
    //while loop with condition to show next waxing moon date.
    while (true) {
        if (getMoonPhase(date) == "Waxing Moon") return date
        date = date.plusDays(1)
    }
}

fun showNearbySalons() {
    println("\n💇‍♀️ Nearby Recommended Salons:")
    //creating list of salons
    val salons = listOf(
        "Nani's Colors – Carrera 36a #16-18",
        "Glow & Flow Hair Studio – Carrera 7 #45-21",
        "MoonaCuts – Avenida Luna #99-01"
    )
    //looping through my list of Salons and printing them out using it as a default keyword for my list values
    salons.forEach { println("- $it") }
}