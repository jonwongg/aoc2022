val weaponScores = Map("rock" -> 1, "paper" -> 2, "scissors" -> 3)
val resultScores = Map("L" -> 0, "D" -> 3, "W" -> 6)
val leftWeapons = Map("A" -> "rock", "B" -> "paper", "C" -> "scissors")
val rightWeapons = Map("X" -> "rock", "Y" -> "paper", "Z" -> "scissors")
val matchFix = Map("X" -> "L", "Y" -> "D", "Z" -> "W")
val weaknessOf = Map("rock" -> "scissors", "scissors" -> "paper", "paper" -> "rock")
val weakTo = weaknessOf.map(_.swap)

def fight(leftEncoded: String, rightEncoded: String) = {
    val leftHand = leftWeapons(leftEncoded)
    val rightHand = rightWeapons(rightEncoded)

    val score = (rightHand, leftHand) match {
        case (rightHand, leftHand) if (rightHand == leftHand) => 3
        case (rightHand, leftHand) if (weaknessOf(rightHand) == leftHand) => 6
        case _ => 0
    }

    score + weaponScores(rightHand)
}

def matchfix(leftEncoded: String, rightEncoded: String) = {
    val leftHand = leftWeapons(leftEncoded)
    val matchOutcome = matchFix(rightEncoded)

    val weaponScore = (matchOutcome, leftHand) match {
        case ("L", leftHand) => weaponScores(weaknessOf(leftHand))
        case ("W", leftHand) => weaponScores(weakTo(leftHand))
        case _ => weaponScores(leftHand)
    }
    
    resultScores(matchOutcome) + weaponScore
}

val source = io.Source.fromFile("2.txt")
var finalScore = 0 // var aka mutable because i am a FAILURE
var matchFixedScore = 0

for (line <- source.getLines) {
    val arr = line.split("\\s+").map(_.trim)
    finalScore += fight(leftEncoded = arr(0), rightEncoded = arr(1))
    matchFixedScore += matchfix(leftEncoded = arr(0), rightEncoded = arr(1))
}

println(finalScore)
println(matchFixedScore)

source.close()