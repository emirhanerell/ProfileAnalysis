# 🧠 Profile Analysis

## 📝 Proje Hakkında

**Profile Analysis**, kullanıcı profillerini analiz etmek ve sunucudan aldığımız verileri **ApexCharts** kullanarak görselleştirmek, loglamak ve veri tabanına kaydetmek için geliştirilmiş bir **Spring Boot** uygulamasıdır.

### 📡 Toplanan Sunucu Verileri (10 saniyede bir):

- 🖥️ CPU kullanımı (`cpuUsage`)  
- 🧠 Bellek kullanımı (`memoryUsage`)  
- 💾 Disk kullanımı (`diskUsage`)  
- 🌐 Ağ kullanımı (`networkUsage`)  
- 🏷️ Sunucu adı (`serverName`)  
- 📍 Sunucu IP adresi (`serverIp`)  
- 📅 Kontrol tarihi (`checkDate`)  

### 📥 İstemci İstek Verileri (Interceptor ile):

- 🕓 İstek tarihi (`requestDate`)  
- 🕑 Yanıt tarihi (`responseDate`)  
- ✅ Durum kodu (`status`)  
- 🔗 Endpoint bilgisi (`endpoint`)  
- 💻 İşletim sistemi (`operatingSystem`)  
- 🔍 HTTP metodu (`method`)  
- ⏱️ İşlem süresi (`duration`)  
- 📄 İstek içeriği (`requestContent`)  
- 📄 Yanıt içeriği (`responseContent`)  
- 🌐 IP adresi (`ip`)  
- 🧭 Tarayıcı bilgisi (`browser`)  

---

## 🚀 Teknolojiler

- ☕ **Java 17**  
- ⚙️ **Spring Boot 3.4.3**  
- 🗄️ **Spring Data JPA**  
- 🌐 **Spring Web**  
- 🧾 **Thymeleaf**  
- 🧰 **Maven**  

---

## 🤝 Katkıda Bulunma

1. Bu depoyu **fork** edin  
2. Yeni bir branch oluşturun:  
   ```bash
   git checkout -b feature/amazing-feature
