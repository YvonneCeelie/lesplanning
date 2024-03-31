package nl.lisaveldhuisen.les.event.repository;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.YearMonth;

@Converter(autoApply = true)
public class YearMonthConverter implements AttributeConverter<YearMonth, Integer> {
    @Override
    public Integer convertToDatabaseColumn(YearMonth yearMonth) {
        return yearMonth == null ? null : (yearMonth.getYear() * 100) + yearMonth.getMonth().getValue();
    }

    @Override
    public YearMonth convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : YearMonth.of(dbData / 100, dbData % 100);
    }
}
