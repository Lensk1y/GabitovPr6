import kotlin.random.Random

fun main() {
    val cities = listOf("Бийск", "Барнаул", "Новосибирск", "Омск", "Томск", "Кемерово", "Новокузнецк", "Красноярск", "Иркутск", "Краснодар", "Сочи", "Ростов-на-Дону", "Волгоград", "Самара", "Екатеринбург")

    var continueWork = true
    while (continueWork) {
        println("Хотите составить поезд? (YES/NO)")
        val userInput = readLine()!!.toUpperCase()
        if (userInput == "YES") {
            val direction = createDirection(cities)
            val passengers = sellTickets()
            val train = formTrain(passengers)
            sendTrain(direction, train)
        } else if (userInput == "NO") {
            continueWork = false
        } else {
            println("Некорректный ввод, попробуйте снова.")
        }
    }
}

fun createDirection(cities: List<String>): Pair<String, String> {
    val fromCity = cities.random()
    var toCity = cities.random()
    while (toCity == fromCity) {
        toCity = cities.random()
    }
    println("Создано направление: $fromCity - $toCity")
    return Pair(fromCity, toCity)
}

fun sellTickets(): Int {
    val passengers = Random.nextInt(5, 201)
    println("Продано билетов: $passengers")
    return passengers
}

fun formTrain(passengers: Int): List<Int> {
    var remainingPassengers = passengers
    val train = mutableListOf<Int>()
    while (remainingPassengers > 0) {
        val capacity = Random.nextInt(5, 26)
        if (remainingPassengers >= capacity) {
            train.add(capacity)
            remainingPassengers -= capacity
        } else {
            train.add(remainingPassengers)
            remainingPassengers = 0
        }
    }
    return train;
}

fun sendTrain(direction: Pair<String, String>, train: List<Int>) {
    println("Поезд ${direction.first} - ${direction.second} отправлен, состоящий из ${train.size} вагонов.")
    for ((index, capacity) in train.withIndex()) {
        println("Вагон ${index + 1}: вместимость - $capacity, пассажиров - $capacity")
    }
}