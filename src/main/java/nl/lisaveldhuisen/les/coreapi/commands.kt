package nl.lisaveldhuisen.les.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.time.Duration
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*

data class RegistreerKlant(
    @TargetAggregateIdentifier
    val klantId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val email:String
)
data class WijzigKlant(
    @TargetAggregateIdentifier
    val klantId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val email:String
)
data class VerwijderKlant(
    @TargetAggregateIdentifier
    val klantId: UUID
)

data class RegistreerLocatie(
    @TargetAggregateIdentifier
    val locatieId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val latitude: Double,
    val longitude: Double
)
data class UpdateLocatie(
    @TargetAggregateIdentifier
    val locatieId: UUID,
    val naam: String,
    val straat: String,
    val postCode: String,
    val woonplaats: String,
    val latitude: Double,
    val longitude: Double
)

data class VerwijderLocatie(
    @TargetAggregateIdentifier
    val locatieId: UUID,
)

data class AddMaandPlanning(
    @TargetAggregateIdentifier
    val maand: YearMonth
)

data class PlanLes(
    @TargetAggregateIdentifier
    val maand: YearMonth,
    val lesId: UUID,
    val klantId: UUID,
    val datumTijdStart: LocalDateTime,
    val duur: Duration,
    val locatieId: UUID
)

data class VerplaatsLes(
    @TargetAggregateIdentifier
    val maand:YearMonth,
    val lesId: UUID,
    val klantId: UUID,
    val datumTijdStartOud: LocalDateTime,
    val duurOud: Duration,
    val datumTijdStartNieuw: LocalDateTime,
    val duurNieuw: Duration,
    val locatieId:UUID
)
data class AnnuleerLes(
    @TargetAggregateIdentifier
    val maand:YearMonth,
    val lesId: UUID)