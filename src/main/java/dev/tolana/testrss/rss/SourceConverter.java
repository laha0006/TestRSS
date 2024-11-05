package dev.tolana.testrss.rss;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SourceConverter implements AttributeConverter<Source, String> {
    @Override
    public String convertToDatabaseColumn(Source source) {
        if (source == null) return null;
        return source.getCode();
    }

    @Override
    public Source convertToEntityAttribute(String s) {
        if (s == null) return null;
        for (Source source : Source.values()) {
            if (source.getCode().equals(s)) return source;
        }
        throw new IllegalArgumentException();
    }
}
