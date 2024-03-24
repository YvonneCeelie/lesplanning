package nl.lisaveldhuisen.les.coreapi

import java.time.Duration
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*

data class MaandPlanningAdded(
    val maand: YearMonth
)

data class LesGepland(
    val maand: YearMonth,
    val lesId: UUID,
    val klantId: UUID,
    val datumTijdStart: LocalDateTime,
    val duur: Duration,
    val locatieId:UUID
)
data class LesVerplaatst(
    val maand: YearMonth,
    val lesId: UUID,
    val klantId: UUID,
    val datumTijdStartOud: LocalDateTime,
    val duurOud: Duration,
    val datumTijdStartNieuw: LocalDateTime,
    val duurNieuw: Duration,
    val locatieId:UUID
)
data class LesGeannuleerd(
    val maand:YearMonth,
    val lesId: UUID
)
data class KlantGeregistreerd(
    val klantId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String
)
data class LocatieGeregistreerd(
    val locatieId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String
)
data class KlantVerwijderd(
    val klantId: UUID
)
data class LocatieVerwijderd(
    val locatieId: UUID
)
