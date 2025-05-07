📝 Proje Hakkında

Profile Analysis, kullanıcı profillerini analiz etmek ve sunucudan aldığımız verileri ApexCharts kullanarak görselleştirmek, loglamak ve veri tabanına kaydetmek için geliştirilmiş bir Spring Boot uygulamasıdır. Sunucunun aşağıdaki verilerini 10 saniyede bir alıyoruz:

CPU kullanımı (cpuUsage)
Bellek kullanımı (memoryUsage)
Disk kullanımı (diskUsage)
Ağ kullanımı (networkUsage)
Sunucu adı (serverName)
Sunucu IP adresi (serverIp)
Kontrol tarihi (checkDate)

İstemcimiz testController'a istek attığında Interceptor kullanarak aşağıdaki verilerini analiz ediyoruz:

İstek tarihi (requestDate)
Yanıt tarihi (responseDate)
Durum kodu (status)
Endpoint bilgisi (endpoint)
İşletim sistemi (operatingSystem)
HTTP metodu (method)
İşlem süresi (duration)
İstek içeriği (requestContent)
Yanıt içeriği (responseContent)
IP adresi (ip)
Tarayıcı bilgisi (browser)

🚀 Teknolojiler

Java 17

Spring Boot 3.4.3

Spring Data JPA

Spring Web

Thymeleaf

Maven

## 🤝 Katkıda Bulunma
1. Bu depoyu fork edin
2. Yeni bir branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun
