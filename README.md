# xui-telegram-bot

# معرفی اجمالی
این نرم افزار یک ربات تلگرام است که به شما امکان استفاده از پنل XUI می دهد

شما با استفاده از این ربات تلگرامی می توانید اقدام به فروش اکانت های VLESS, VMESS کنید



### راه اندازی روی سرور
* ساخت ربات تلگرامی از طریق https://t.me/BotFather
* نصب Docker, Docker compose
* دانلود Docker compose
* `curl -L https://raw.githubusercontent.com/mhsenpc/xui-telegram-bot/main/docker-compose.yml -o docker-compose.yml`
* `mkdir storage`
* `nano storage/config.json`
```
{
    "BOT_HOST_URL": "https://bot.example.com:8888",
    "PANEL_BASE_URL": "https://example.com",
    "PANEL_USERNAME": "admin",
    "PANEL_PASSWORD": "password123",
    "INBOUND_ID_OVERRIDE": "3",
    "VPN_HOST": "vpn.example.com",
    "VPN_PORT": "8080",
    "SAVE_ORDER_MESSAGE": "لطفا مبلغ را به شماره کارت ۲۳۲۳۲۳۲۳۲۳۲۳ واریز کنید. در اسرع وقت اکانت شما آماده خواهد شد",
    "BOT_TOKEN": "abcdef123456"
}
```
* docker-compose up -d

## Generate self signed certificate
برای ارتباط با سرورهای تلگرام ربات شما نیاز به یک ارتباط امن از نوع HTTPS دارد. برای تولید کلید همچنین نیاز به یک دامنه دارید. دامنه خود را با bot.ferfere.de عوض کنید و دستورات زیر را اچرا کنید
```
openssl req -x509 -newkey rsa:4096 -keyout private.key -out cert.crt -days 365 -nodes -subj "/CN=bot.ferfere.de"
openssl pkcs12 -export -out keystore.p12 -inkey private.key -in cert.crt -name myalias
```


    

## تصاویری از ربات

<img width="420" alt="image" src="https://github.com/user-attachments/assets/d19a97fe-2054-4602-b492-9bfde7aeec8a">


![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/8ba2572b-d98d-4592-9dcf-38e9b99704de)
![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/1a101033-87fc-47de-a837-13e08f41a7b6)
![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/46c603aa-39db-49a7-a375-56780dbb5b71)
![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/efcf034c-7fed-4161-9b3f-7a687c5e8fad)
![image](https://github.com/mhsenpc/xui-telegram-bot/assets/5123843/f59cc7b2-3913-4f01-a781-2b52b093e2ed)
<img width="661" alt="image" src="https://github.com/user-attachments/assets/62f5f9b4-45ca-49ba-a62b-03dd2f026d2c">
<img width="668" alt="image" src="https://github.com/user-attachments/assets/eaed2b70-763e-4cc8-97ef-4240fa374488">
