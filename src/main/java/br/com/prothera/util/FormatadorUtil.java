package br.com.prothera.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorUtil {

    private static final Locale LOCALE_BR = Locale.forLanguageTag("pt-BR");
    private static final DecimalFormatSymbols SIMBOLOS = new DecimalFormatSymbols(LOCALE_BR);
    private static final DecimalFormat FORMATO_MOEDA = new DecimalFormat("#,##0.00", SIMBOLOS);
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private FormatadorUtil() {
        // Construtor privado para impedir instanciação (classe utilitária)
    }

    /**
     * Formata um BigDecimal no padrão brasileiro (ex: 1.212,00).
     */
    public static String formatarMoeda(BigDecimal valor) {
        return FORMATO_MOEDA.format(valor);
    }

    /**
     * Formata uma data no padrão brasileiro (ex: 18/10/2000).
     */
    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }
}
