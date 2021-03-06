# Общее описание “PolitRange”.
Комплекс программ “PolitRange” в дальнейшем **«PR»**,был создан в рамках стажировки выпускниками “GeekBrains” [http://geekbrains.ru/](http://geekbrains.ru/)

**1. Миссия.**

Ежедневный сбор статистических данных, о политических деятелях, на ресурсах сети интернет. Представление результатов в виде информативных таблиц, для анализа конечным пользователем. Уже сейчас Вы можете [скачать] (https://github.com/SuperBugCompany/politrange/releases/download/Android0.1/PolitRange.zip) наше мобильное приложение и узнать, кто из политических деятелей России популярнее. Причем сама реализация, всего комплекса является достаточно  универсальной и расширяемой, что позволяет его применять и в других сферах, например реклама (оценка привлекательности размещения рекламного контента),  хотя прямыми потенциальными клиентами проекта являются именно рейтинговые агентства.

**2. Состав**

PR состоит из трех основных частей:

* серверная часть  или поставщик данных –  веб-сервис ([ссылка] (https://github.com/SuperBugCompany/politrange.webapi/releases/download/v0.1.4/SuperBug.Politrange.WebApi-v.0.1.4.zip) для скачивания) и краулер ([ссылка] (https://github.com/SuperBugCompany/politrange.webapi/releases/download/v0.1.4/SuperBug.Politrange.Crawler-v0.1.4.zip) для скачивания);
* административная часть ([ссылка] (https://github.com/SuperBugCompany/politrange/releases/download/Admintool3/SetupPolitRangeAdminTool.zip) для скачивания);
* аналитическая часть ([ссылка] (https://github.com/SuperBugCompany/politrange/releases/download/Android0.2/PolitRange.zip) для скачивания);

**Серверная часть**, далее **«СЧ»** может быть размещена на компьютере Вашей компании, либо на хостинге в интернете.  **"СЧ"** работает, только под управлением Windows, не ниже Windows 7 и требует предустановленных **MySQL Server** и **Internet Information Services**.

**Административная часть**, далее **«АЧ»** может быть размещена на любом компьютере, имеющего подключение к сети интернет, либо сети организации, где установлена **«СЧ»**. Также,**«АЧ»** может быть установлена, на компьютер **«СЧ»**.
В **«АЧ»** сотрудник заполняет следующие справочники:
*  справочник личностей далее **«СЛ»**; 
*  справочник ключевых слов далее **«КС»**; 
*  справочник сайтов **«СС»**.

Первым заполняется **"СЛ"**, далее справочник сайтов. Записи в справочник  **"КС"**вносятся на основе **"СЛ"**. Приведем на следующей странице примеры заполнения:

1.справочник личности

![справочник личности](https://github.com/SuperBugCompany/politrange/blob/master/admintool/screenshots/scr01.jpg)

2.справочник сайты

![справочник сайты](https://github.com/SuperBugCompany/politrange/blob/master/admintool/screenshots/scr06.jpg)

3.справочник ключевых слов

![справочник ключевые слова](https://github.com/SuperBugCompany/politrange/blob/master/admintool/screenshots/scr03.jpg)

**Аналитическая часть**, далее **«АНЧ»**, выполнена в виде мобильного приложения для смартфонов и планшетов на базе ос Android. Требует подключения к сети интернет, для установки соединения с **«СЧ»**. По результатам работы **"СЧ"**, мобильное приложение строит два отчета:

* общая  статистика, далее **«ОБЩС»** ;
* ежедневная статистика,  далее **«EC»**. 

**"ОБЩС"** – после выбора сайта, формируется таблица из двух колонок, где «Имя» – это личность из **"СЛ"** и «Количество упоминаний» - это количество ключевых слов из **КС**, на анализируемом ресурсе.

![первый отчет](https://github.com/SuperBugCompany/politrange/blob/master/admintool/screenshots/abdr_scr01.png)

По данной таблице можно сделать вывод, об общей популярности личности, среди введенных в **"СЛ"**.

**"ЕС"** - после выбора сайта и периода, формируется список в виде открывающихся ячеек, отсортированных по дате. Каждая открытая ячейка включает в себя **Имя** и **Ранг** политика.

![](https://github.com/SuperBugCompany/politrange/blob/master/admintool/screenshots/abdr_scr02.png)

По желанию покупателя **"PR"**, разработчики могут расширить спектр аналитических отчетов, а также увеличить количество входных данных для анализа. Приобретя **"PR"**, Вы получаете поддержку и внедрение, напрямую от разработчика. Также ждем предложений, по улучшению продукта и сотрудничества.

PolitRange является проектом с открытым исходным кодом, тут вы можете скачать исходный код для:
* веб-сервис и краулер ([ссылка] (https://github.com/SuperBugCompany/politrange.webapi/archive/v0.1.4.zip) для скачивания);
* административная часть ([ссылка] (https://github.com/SuperBugCompany/politrange/archive/Admintool3.zip) для скачивания);
* аналитическая часть ([ссылка] (https://github.com/SuperBugCompany/politrange/archive/Android0.1.zip) для скачивания);


С уважением, 
коллектив SuperBugCompany.
