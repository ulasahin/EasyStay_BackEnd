package com.example.easystay.core.exceptionhandling.exception.problemdetails;

public final class ErrorMessages {
    private ErrorMessages() {
        throw new UnsupportedOperationException("Bu sabit bir sınıftır ve örneklenemez.");
    }
    public static final String EMAİL_USED = "Bu e-mail daha önce kullanılmıştır.";
    public static final String EMAİL_NOT_FOUND = "Böyle bir e-mail bulunamamıştır.";
    public static final String NOT_SAME_PASS = "Şifreler aynı değil.";
    public static final String ROOM_FULL = "Bu oda doludur.";
    public static final String ROOM_NUMBER_NOT_FOUND = "Böyle bir oda numarasına sahip oda bulunmaktadır.";
    public static final String INVALID_PASS_EMAİL = "E-posta ya da şifre yanlıştır.";
    public static final String USER_NOT_FOUND = "Böyle bir kullanıcı bulunamadı.";
    public static final String ROOM_NOT_FOUND = "Böyle bir oda bulunamadı.";
    public static final String RESEVR_NOT_EXİST = "Böyle bir rezervasyonunuz yoktur.";
    public static final String RESEVR_NOT_FOUND = "Rezervasyon bulunamadı.";
    public static final String USER_NOT_FOUND_FOR_EMAİL = "Şu e-postaya ait kullanıcı bulunamadı: ";
    public static final String NOT_BLANK_EMAİL = "Email alanı boş olamaz.";
    public static final String NOT_BLANK_PASS = "Şifre alanı boş olamaz.";
    public static final String REGEXP_FOR_PASS = "Şifre en az bir numerik, en az bir tane büyük harf içermeli ve en az 6 karakter olmalıdır.";
    public static final String INVALİD_EMAİL = "Geçerli bir e-mail giriniz.";
    public static final String NOT_BLANK_FIRST_NAME = "İsim kısmı boş olamaz.";
    public static final String NOT_BLANK_LAST_NAME = "Soyisim kısmı boş olamaz.";
    public static final String FİRST_NAME_SIZE_3_50 = "İsim 3 ila 50 karakter arasında olabilir.";
    public static final String LAST_NAME_SIZE_3_50 = "Soyisim 3 ila 50 karakter arasında olabilir.";
    public static final String NOT_BLANK_NUMBER = "Numara kısmı boş olamaz.";
    public static final String JUST_NUMERIC_CHAR = "Numara sadece sayısal ifadeler içermelidir.";
    public static final String NUMBER_SIZE_10 = "Numara sadece sayısal ifadeler içermelidir.";
    public static final String NOT_BLANK_ROOM_NUMBER = "Oda numarası kısmı boş olamaz.";
    public static final String NOT_BLANK_ROOM_PRİCE = "Ücret kısmı boş olamaz.";
    public static final String NOT_BLANK_ROOM_STATUS = "Durum kısmı boş olamaz.";
    public static final String NOT_BLANK_ROOM_TYPE = "Oda tipi boş olamaz.";
    public static final String JUST_NUMERİC_CHAR_FOR_ROOM_NUMBER = "Oda numarası sadece numerik ifadeler içermelidir.";
    public static final String JUST_NUMERİC_CHAR_FOR_ROOM_PRİCE = "Ücret kısmı sadece numerik ifadeler içermelidir.";

}
