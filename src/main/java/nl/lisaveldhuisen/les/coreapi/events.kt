package nl.lisaveldhuisen.les.coreapi

import java.time.Duration
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*

data class MaandPlanningAdded(
    val maand: YearMonth
)

interface LesEvent{
    val maand: YearMonth
    val lesId: UUID
    val klantId: UUID
    val datumTijdStart: LocalDateTime
    val duur: Duration
    val locatieId:UUID
}

data class LesGepland (
    override val maand: YearMonth,
    override val lesId: UUID,
    override val klantId: UUID,
    override val datumTijdStart: LocalDateTime,
    override val duur: Duration,
    override val locatieId: UUID
) : LesEvent
data class LesVerplaatst(
    override val maand: YearMonth,
    override val lesId: UUID,
    override val klantId: UUID,
    val datumTijdStartOud: LocalDateTime,
    val duurOud: Duration,
    override val datumTijdStart: LocalDateTime,
    override val duur: Duration,
    override val locatieId:UUID
) : LesEvent
data class LesGeannuleerd(
    val maand:YearMonth,
    val lesId: UUID
)
data class KlantGeregistreerd(
    val klantId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val email: String
)
data class LocatieGeregistreerd(
    val locatieId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val longitude:Double,
    val latitude: Double
)

data class KlantGewijzigd(
    val klantId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val email: String
)
data class LocatieGewijzigd(
    val locatieId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val longitude:Double,
    val latitude: Double
)
data class KlantVerwijderd(
    val klantId: UUID
)
data class LocatieVerwijderd(
    val locatieId: UUID
)
