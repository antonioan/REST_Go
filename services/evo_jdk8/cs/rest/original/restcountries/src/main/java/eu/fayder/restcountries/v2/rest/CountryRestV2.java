/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package eu.fayder.restcountries.v2.rest;

import com.google.gson.*;
import eu.fayder.restcountries.domain.ResponseEntity;
import eu.fayder.restcountries.v2.domain.Country;
import eu.fayder.restcountries.v2.domain.Language;
import eu.fayder.restcountries.domain.ICountryRestSymbols;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Path("/v2")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CountryRestV2 {

    private static final Logger LOG = Logger.getLogger(CountryRestV2.class);

    @GET
    @Path("all")
    public Object getAllCountries(@QueryParam("fields") String fields) {
        return this.getCountries(fields);
    }

    @GET
    @Path("languages")
    public Object getAllLanguages(@QueryParam("fields") String fields) {
        return Arrays.asList(new Language("af", "afr", "Afrikaans", "Afrikaans"),
                                        new Language("am", "amh", "Amharic", "አማርኛ"),
                                        new Language("ar", "ara", "Arabic", "العربية"),
                                        new Language("ay", "aym", "Aymara", "aymar aru"),
                                        new Language("az", "aze", "Azerbaijani", "azərbaycan dili"),
                                        new Language("be", "bel", "Belarusian", "беларуская мова"),
                                        new Language("bg", "bul", "Bulgarian", "български език"),
                                        new Language("bi", "bis", "Bislama", "Bislama"),
                                        new Language("bn", "ben", "Bengali", "বাংলা"),
                                        new Language("bs", "bos", "Bosnian", "bosanski jezik"),
                                        new Language("ca", "cat", "Catalan", "català"),
                                        new Language("ch", "cha", "Chamorro", "Chamoru"),
                                        new Language("cs", "ces", "Czech", "čeština"),
                                        new Language("da", "dan", "Danish", "dansk"),
                                        new Language("de", "deu", "German", "Deutsch"),
                                        new Language("dv", "div", "Divehi", "ދިވެހި"),
                                        new Language("dz", "dzo", "Dzongkha", "རྫོང་ཁ"),
                                        new Language("el", "ell", "Greek (modern)", "ελληνικά"),
                                        new Language("en", "eng", "English", "English"),
                                        new Language("es", "spa", "Spanish", "Español"),
                                        new Language("et", "est", "Estonian", "eesti"),
                                        new Language("fa", "fas", "Persian (Farsi)", "فارسی"),
                                        new Language("ff", "ful", "Fula", "Fulfulde"),
                                        new Language("fi", "fin", "Finnish", "suomi"),
                                        new Language("fj", "fij", "Fijian", "vosa Vakaviti"),
                                        new Language("fo", "fao", "Faroese", "føroyskt"),
                                        new Language("fr", "fra", "French", "français"),
                                        new Language("ga", "gle", "Irish", "Gaeilge"),
                                        new Language("gn", "grn", "Guaraní", "Avañe'ẽ"),
                                        new Language("gv", "glv", "Manx", "Gaelg"),
                                        new Language("he", "heb", "Hebrew (modern)", "עברית"),
                                        new Language("hi", "hin", "Hindi", "हिन्दी"),
                                        new Language("hr", "hrv", "Croatian", "hrvatski jezik"),
                                        new Language("ht", "hat", "Haitian", "Kreyòl ayisyen"),
                                        new Language("hu", "hun", "Hungarian", "magyar"),
                                        new Language("hy", "hye", "Armenian", "Հայերեն"),
                                        new Language("id", "ind", "Indonesian", "Bahasa Indonesia"),
                                        new Language("is", "isl", "Icelandic", "Íslenska"),
                                        new Language("it", "ita", "Italian", "Italiano"),
                                        new Language("ja", "jpn", "Japanese", "日本語 (にほんご)"),
                                        new Language("ka", "kat", "Georgian", "ქართული"),
                                        new Language("kg", "kon", "Kongo", "Kikongo"),
                                        new Language("kk", "kaz", "Kazakh", "қазақ тілі"),
                                        new Language("kl", "kal", "Kalaallisut", "kalaallisut"),
                                        new Language("km", "khm", "Khmer", "ខ្មែរ"),
                                        new Language("ko", "kor", "Korean", "한국어"),
                                        new Language("ku", "kur", "Kurdish", "Kurdî"),
                                        new Language("ky", "kir", "Kyrgyz", "Кыргызча"),
                                        new Language("la", "lat", "Latin", "latine"),
                                        new Language("lb", "ltz", "Luxembourgish", "Lëtzebuergesch"),
                                        new Language("ln", "lin", "Lingala", "Lingála"),
                                        new Language("lo", "lao", "Lao", "ພາສາລາວ"),
                                        new Language("lt", "lit", "Lithuanian", "lietuvių kalba"),
                                        new Language("lu", "lub", "Luba-Katanga", "Tshiluba"),
                                        new Language("lv", "lav", "Latvian", "latviešu valoda"),
                                        new Language("mg", "mlg", "Malagasy", "fiteny malagasy"),
                                        new Language("mh", "mah", "Marshallese", "Kajin M̧ajeļ"),
                                        new Language("mi", "mri", "Māori", "te reo Māori"),
                                        new Language("mk", "mkd", "Macedonian", "македонски јазик"),
                                        new Language("mn", "mon", "Mongolian", "Монгол хэл"),
                                        new Language("ms", "msa", "Malay", "bahasa Melayu"),
                                        new Language("mt", "mlt", "Maltese", "Malti"),
                                        new Language("my", "mya", "Burmese", "ဗမာစာ"),
                                        new Language("na", "nau", "Nauruan", "Dorerin Naoero"),
                                        new Language("nb", "nob", "Norwegian Bokmål", "Norsk bokmål"),
                                        new Language("nd", "nde", "Northern Ndebele", "isiNdebele"),
                                        new Language("ne", "nep", "Nepali", "नेपाली"),
                                        new Language("nl", "nld", "Dutch", "Nederlands"),
                                        new Language("nn", "nno", "Norwegian Nynorsk", "Norsk nynorsk"),
                                        new Language("no", "nor", "Norwegian", "Norsk"),
                                        new Language("nr", "nbl", "Southern Ndebele", "isiNdebele"),
                                        new Language("ny", "nya", "Chichewa", "chiCheŵa"),
                                        new Language("pa", "pan", "(Eastern) Punjabi", "ਪੰਜਾਬੀ"),
                                        new Language("pl", "pol", "Polish", "język polski"),
                                        new Language("ps", "pus", "Pashto", "پښتو"),
                                        new Language("pt", "por", "Portuguese", "Português"),
                                        new Language("qu", "que", "Quechua", "Runa Simi"),
                                        new Language("rn", "run", "Kirundi", "Ikirundi"),
                                        new Language("ro", "ron", "Romanian", "Română"),
                                        new Language("ru", "rus", "Russian", "Русский"),
                                        new Language("rw", "kin", "Kinyarwanda", "Ikinyarwanda"),
                                        new Language("sg", "sag", "Sango", "yângâ tî sängö"),
                                        new Language("si", "sin", "Sinhalese", "සිංහල"),
                                        new Language("sk", "slk", "Slovak", "slovenčina"),
                                        new Language("sl", "slv", "Slovene", "slovenski jezik"),
                                        new Language("sm", "smo", "Samoan", "gagana fa\'a Samoa"),
                                        new Language("sn", "sna", "Shona", "chiShona"),
                                        new Language("so", "som", "Somali", "Soomaaliga"),
                                        new Language("sq", "sqi", "Albanian", "Shqip"),
                                        new Language("sr", "srp", "Serbian", "српски језик"),
                                        new Language("ss", "ssw", "Swati", "SiSwati"),
                                        new Language("st", "sot", "Southern Sotho", "Sesotho"),
                                        new Language("sv", "swe", "Swedish", "svenska"),
                                        new Language("sw", "swa", "Swahili", "Kiswahili"),
                                        new Language("ta", "tam", "Tamil", "தமிழ்"),
                                        new Language("tg", "tgk", "Tajik", "тоҷикӣ"),
                                        new Language("th", "tha", "Thai", "ไทย"),
                                        new Language("ti", "tir", "Tigrinya", "ትግርኛ"),
                                        new Language("tk", "tuk", "Turkmen", "Türkmen"),
                                        new Language("tn", "tsn", "Tswana", "Setswana"),
                                        new Language("to", "ton", "Tonga (Tonga Islands)", "faka Tonga"),
                                        new Language("tr", "tur", "Turkish", "Türkçe"),
                                        new Language("ts", "tso", "Tsonga", "Xitsonga"),
                                        new Language("uk", "ukr", "Ukrainian", "Українська"),
                                        new Language("ur", "urd", "Urdu", "اردو"),
                                        new Language("uz", "uzb", "Uzbek", "Oʻzbek"),
                                        new Language("ve", "ven", "Venda", "Tshivenḓa"),
                                        new Language("vi", "vie", "Vietnamese", "Tiếng Việt"),
                                        new Language("xh", "xho", "Xhosa", "isiXhosa"),
                                        new Language("zh", "zho", "Chinese", "中文 (Zhōngwén)"),
                                        new Language("zu", "zul", "Zulu", "isiZulu"),
                                        new Language(null, "zsm", "Malaysian", "بهاس مليسيا"));
    }

    @GET
    public Object getCountries(@QueryParam("fields") String fields) {
        LOG.info("Getting all");
        List<Country> countries = CountryService.getInstance().getAll();
        return parsedCountries(countries, fields);
    }

    @GET
    @Path("alpha/{alphacode}")
    public Object getByAlpha(@PathParam("alphacode") String alpha, @QueryParam("fields") String fields) {
        LOG.info("Getting by alpha " + alpha);
        if (isEmpty(alpha) || alpha.length() < 2 || alpha.length() > 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        Country country = CountryService.getInstance().getByAlpha(alpha);
        if (country != null) {
            return parsedCountry(country, fields);
        }
        return getResponse(Response.Status.NOT_FOUND);
    }

    @GET
    @Path("alpha/")
    public Object getByAlphaList(@QueryParam("codes") String codes, @QueryParam("fields") String fields) {
        LOG.info("Getting by list " + codes);
        if (isEmpty(codes) || codes.length() < 2 || (codes.length() > 3 && !codes.contains(";"))) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = CountryService.getInstance().getByCodeList(codes);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("currency/{currency}")
    public Object getByCurrency(@PathParam("currency") String currency, @QueryParam("fields") String fields) {
        LOG.info("Getting by currency " + currency);
        if (isEmpty(currency) || currency.length() != 3) {
            return getResponse(Response.Status.BAD_REQUEST);
        }
        try {
            List<Country> countries = CountryService.getInstance().getByCurrency(currency);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("name/{name}")
    public Object getByName(@PathParam("name") String name, @QueryParam("fullText") boolean fullText, @QueryParam("fields") String fields) {
        LOG.info("Getting by name " + name);
        try {
            List<Country> countries = CountryService.getInstance().getByName(name, fullText);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("callingcode/{callingcode}")
    public Object getByCallingCode(@PathParam("callingcode") String callingcode, @QueryParam("fields") String fields) {
        LOG.info("Getting by calling code " + callingcode);
        try {
            List<Country> countries = CountryService.getInstance().getByCallingCode(callingcode);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("capital/{capital}")
    public Object getByCapital(@PathParam("capital") String capital, @QueryParam("fields") String fields) {
        LOG.info("Getting by capital " + capital);
        try {
            List<Country> countries = CountryService.getInstance().getByCapital(capital);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("region/{region}")
    public Object getByRegion(@PathParam("region") String region, @QueryParam("fields") String fields) {
        LOG.info("Getting by region " + region);
        try {
            List<Country> countries = CountryService.getInstance().getByRegion(region);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("subregion/{subregion}")
    public Object getBySubRegion(@PathParam("subregion") String subregion, @QueryParam("fields") String fields) {
        LOG.info("Getting by sub region " + subregion);
        try {
            List<Country> countries = CountryService.getInstance().getBySubRegion(subregion);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("lang/{lang}")
    public Object getByLanguage(@PathParam("lang") String language, @QueryParam("fields") String fields) {
        LOG.info("Getting by language " + language);
        try {
            List<Country> countries = CountryService.getInstance().getByLanguage(language);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("demonym/{demonym}")
    public Object getByDemonym(@PathParam("demonym") String demonym, @QueryParam("fields") String fields) {
        LOG.info("Getting by demonym " + demonym);
        try {
            List<Country> countries = CountryService.getInstance().getByDemonym(demonym);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("regionalbloc/{regionalbloc}")
    public Object getByRegionalBloc(@PathParam("regionalbloc") String regionalBlock, @QueryParam("fields") String fields) {
        LOG.info("Getting by regional bloc " + regionalBlock);
        try {
            List<Country> countries = CountryService.getInstance().getByRegionalBloc(regionalBlock);
            if (!countries.isEmpty()) {
                return parsedCountries(countries, fields);
            }
            return getResponse(Response.Status.NOT_FOUND);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return getResponse(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Object doPOST() {
        LOG.info("Handling POST Request");
        return getResponse(Response.Status.METHOD_NOT_ALLOWED);
    }

    private Response getResponse(Response.Status status) {
        Gson gson = new Gson();
        return Response
                .status(status)
                .entity(gson.toJson(new ResponseEntity(status.getStatusCode(),
                        status.getReasonPhrase()))).build();
    }

    private Object parsedCountry(Country country, String fields) {
        if (fields == null || fields.isEmpty()) {
            return country;
        } else {
            return getCountryJson(country, Arrays.asList(fields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private Object parsedCountries(List<Country> countries, String excludedFields) {
        if (excludedFields == null || excludedFields.isEmpty()) {
            return countries;
        } else {
            return getCountriesJson(countries, Arrays.asList(excludedFields.split(ICountryRestSymbols.SEMICOLON)));
        }
    }

    private String getCountryJson(Country country, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(gson.toJson(country)).getAsJsonObject();

        List<String> excludedFields = getExcludedFields(fields);
        for (String field : excludedFields) {
            jsonObject.remove(field);
        }
        return jsonObject.toString();
    }

    private String getCountriesJson(List<Country> countries, List<String> fields) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(gson.toJson(countries)).getAsJsonArray();
        JsonArray resultArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);

            List<String> excludedFields = getExcludedFields(fields);
            for (String excludedField : excludedFields) {
                jsonObject.remove(excludedField);
            }
            resultArray.add(jsonObject);
        }
        return resultArray.toString();
    }

    private List<String> getExcludedFields(List<String> fields) {
        List<String> excludedFields = new ArrayList<>(Arrays.asList(COUNTRY_FIELDS));
        excludedFields.removeAll(fields);
        return excludedFields;
    }

    private static final String[] COUNTRY_FIELDS = new String[]{
            "name",
            "topLevelDomain",
            "alpha2Code",
            "alpha3Code",
            "callingCodes",
            "capital",
            "altSpellings",
            "region",
            "subregion",
            "translations",
            "population",
            "latlng",
            "demonym",
            "area",
            "gini",
            "timezones",
            "borders",
            "nativeName",
            "numericCode",
            "currencies",
            "languages",
            "flag",
            "regionalBlocs",
            "cioc"
    };

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
