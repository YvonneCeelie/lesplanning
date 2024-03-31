package nl.lisaveldhuisen.les.coreapi

import java.time.LocalDateTime
import java.util.*

data class KlantGegevensRequest(
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val email: String
)

data class PLanLesRequest(
    val klantId: UUID,
    val datumTijdStart: LocalDateTime,
    val datumTijdEinde: LocalDateTime,
    val locatieId: UUID
)

data class VerplaatsLesRequest(
    val datumTijdStartOud: LocalDateTime,
    val datumTijdEindeOud: LocalDateTime,
    val datumTijdStartNieuw: LocalDateTime,
    val datumTijdEindeNieuw: LocalDateTime,
    val locatieId:UUID
)
data class LocatieGegevensRequest(
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val latitude: Double,
    val longitude: Double
)
data class LoginDto(
        val usernameOrEmail:String,
        val password: String
)

data class JwtAuthResponse @JvmOverloads constructor(
        val accessToken:String,
        val tokenType: String = "Bearer"
)
data class RegisterUserDto(
        val usernameOrEmail:String,
        val password: String
)
data class LoginUserDto(
        val usernameOrEmail:String,
        val password: String
)
data class LoginResponse(
        val token:String,
        val expires:Long
)

