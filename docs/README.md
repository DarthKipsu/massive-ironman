Robottiohjelmoinnin harjoitustyö: Don Quijote
=============================================

Joulu 2014, Verna Koskinen (014427857)

![Robotti ja tuulimyllyt](/docs/kuvat/yleiskuva.jpg)

Robotin kuvaus
--------------

Don Quijote on tuulimyllyjä vastaan käyvä taistelurobotti. Robotti aloittaa skannaamalla ympäristöään kääntymällä itsensä ympäri ja tunnistamalla edessään näkyvät myllyt. Havaitessaan tuulimyllyn se lähestyy tätä ja tunnistaa tuulimyllyn värin, jonka perusteella reagoi eri tavoin eri tuulimyllyihin, yleenäs hyökkäämällä. Suoritettuaan toiminnon, robotti peruuttaa takaisin siihen pisteeseen josta se lähti ja jatkaa ympäristön skannausta, kunnes täysi 360 asteen kierros on tehty.

Tuulimyllyihin reagoidaan seuraavan kaavan mukaisesti:
- Väri epävarma: Täysi hyökkäys, jossa robotti venyttää päänsä ulos ja kiertää 360 asteen kierroksen itsensä ympäri kaataen kaikki reitille osuvat tuulimyllyt
- Musta: Hyökkäys kumoamalla tuulimylly vasemmalta puolelta
- Sininen: Vastaavaa hyökkäys, mutta oikealta puolelta
- Vihreä: Täysiä eteenpäin, robotti yliajaa tuulimyllyn
- Punainen ja ruskea: perushyökkäys edestäpäin, päällä puskemalla
- Keltainen: Robotti ei pidä keltaisesta ja peruuttaa täristen
- Valkoinen: Robotti jättää rauhaan antautuneet valkoiset tuulimyllyt

Mikäli väriä ei pystytä lainkaan lukemaan, robotti ilmoittaa sen piippaamalla varoitusäänen ja jatkaa sen jälkeen uusien kohteiden etsintää. Väriä tunnistettaessa robotti yrittää tätä ennen kuitenkin kääntää itseään niin että värin lukeminen onnistuisi.

Robotin toiminnasta on demo YouTubessa:

[![Siirry videoon](http://img.youtube.com/vi/TdavbKa0eN8/0.jpg)](http://www.youtube.com/watch?v=TdavbKa0eN8)

Robotin rakenne
---------------

Robotti kulkee telaketjuilla, joihin on kiinnitetty molemmat isommat moottorit. Telaketjujen pituus on noin 14,5 cm ja etäisyys toisistaan n. 12 cm. Telaketjujen kiinnityksessä on huomioitu kestävyys. Ne pysyvät paikoillaan vaikka robotti liikkuu tiukkoja 360 asteen kierroksia oman ruumiinsa ympäri, tai ajaa tuulimyllyjen yli (epätasainen maasto).

![Robotti ja tuulimyllyt](/docs/kuvat/rakenne1.jpg)

![Robotti ja tuulimyllyt](/docs/kuvat/rakenne2.jpg)

Kohteiden havaitseminen ja suuri osa taistelutoiminnallisuudesta on pään tarjoamaa. Päässä on kiinni infrapunasensori, sekä värisensori. Pää on noin 1,5cm korkeudella maanpinnasta ja kiinnitettynä pitkään varteen, jota pystytään keskikokoisella moottorilla liikuttamaan eteen ja taakse. Pään liikuttamisesta vastaavat rattaat ovat välillä kovalla rasituksella hyökätessä, joten rattaiden kiinnityksen jämäkkyyteen on kiinnitetty erityishuomiota.

![Robotti ja tuulimyllyt](/docs/kuvat/rakenne3.jpg)

Robotille on lisätty lisäksi haarniskamaisia vahvistuksia rungon ympärille lisäämään taistelurobottimaista ulkonäköä, mutta näillä ei ole mitään käytännön merkitystä toiminnan kannalta.

Tarkemmat rakennusohjeet kuvineen löytyy [rakennusohje kansiosta](/docs/rakennusohje).

Koodin rakenne
--------------

Robotin älyllisen toiminnan kannalta oleellisimmat luokat ovat ObjectFinder, jonka logiikan mukaan robotti kääntyy etsimään edessä olevia kohteita, sekä Examiner, joka ottaa vallan kohteen löydyttyä, käskien robotin lähestymään kohdetta ja reagoimaan siihen toivotulla tavalla. Luokat pitävät myös muistissa tietoa robotin sijainnista milläkin hetkellä, jonka avulla robotti pystyy jatkamaan uusien kohteiden etsimistä ja tuhoamista aina hoideltuaan edellisen kohteen.

Luokkien käyttö on pyritty pitämään yksinkertaisena ja niin julkisia metodeja, joiden avulla kaikki tämä hoituu on molemmissa luokissa vain yksi.

Lisäksi loin luokat robotin moottorien ja sensorien käsittelyyn, jotka varmistavat että kaikki robotin fyysisiä toimintoja on helppo käyttää. Robotin liikkuminen hoituu Motors luokalla, pään liikuttaminen HeadImpl luokalla ja seonsoridatan pyytäminen luokista ColorSensorImpl ja IRSensorImpl.

ObjectFinder ja Examiner luokat tuntee sensoriluokat niille luotujen interfacejen kautta, jotta luokkien logiikan jUnit yksikkötestaus on mahdollista.

Testaus
-------

Robotin toimintaa on testattu ajamalla robotin ohjelma pientenkin uusien toiminnallisuuksien lisäyksien jälkeen ja näin varmistamalla haluttu toiminta koko kehitystyön ajan. Kun kohteiden löytämiseen ja tunnistamiseen tarvittavat metodit toimivat, tein jUnit testit niiden takana olevalle logiikalle varmistaakseni että toiminta pysyy oikeana myös uusia ominaisuuksia lisätessä tai koodia refaktoroidessa. Testit kattavat tärkeimmät rajatapaukset, jotka takaavat robotin oikean reaktion eri tilanteissa.

Mekaanisesti suoritetuilla testeillä on selvitetty lähinnä millä tavoin robotti kykenee reagoimaan kohteisiin:

**Testi 1:**

Testasin robotin kykyä havaita eri etäisyyksillä olevia objekteja asettelemalla legopalikoista koottuja torneja robotin ympärille, kukin aina edellistä kauemmaksi. Robotin tehtävä oli kääntyä itsensä ympäri ja kirjata ylös näkemänsä objektien etäisyydet. Näin sain parin testin aikana melko hyvän käsityksen siitä kuinka kauas sijoitettuja kohteita robotti pystyy havaitsemaan.

Testistä selvisi myös, ettei robotin sensori koskaan mittaa arvoja paljon yli 50 (maksimissaan ehkä 55) vaikka sensorin kuuluisi palauttaa arvoja väliltä 0-100. Näin ollen muutin koodia niin että kaikki yli 50 lukemat jätetään huomiotta, koska robotin sensori luki näitä yli 50 arvoja silloinkin kun edessä ei ollut mitään.

Myöhemmin testatessani robottia eri huoneessa (eri valaistusolot) huomasin robotin näkevän pidemmälle keltaisessa kuin valkoisessa valossa.

**Testi 2:**

Värien erottelu testissä asetin robotin mittaamaan värisensorilla havaitsemiaan arvoja ja kirjaamaan ne ylös. Laitoin värisensorin eteen vuorotellen eri värisiä ja eri materiaalista koostuvia esineitä ja katsoin kuinka tarkasti väri saatiin miltäkin etäisyydeltä mitattua.

Parhaiksi materiaaleiksi osoittautui toiset legopalikat ja pinnoitettu paperi, mutta mittaustuokset olivat hyvin vaihtelevia. Tummia kohteita (kuten useat ruskeat ja viininpunaiset, musta) seonsori ei havainnut lainkaan. Mustaa ja keltaista arvoa en saanut mitattua kertaakaan, kaikki keltaiset palikat ja esineet olivat vihreitä tai ruskeita. Lähes neljännes kaikista mittauksista osoitti ruskeaa, huolimatta edessä olevan esineen väristä. Parhaiten tunnistettavia värejä olivat valkoinen, punainen ja sininen.

Värien tunnistus toimi paremmin lämpimämmän sävyisessä valossa, kuin esim. päivänvalolampun alla. Robotin toiminnan laajempaa testausta lämpimässä valossa ei kuitenkaan pystynyt suorittaa, sillä kotoani ei löydy riittävän isoa tilaa varsinaisen ohjelman ajamiselle keltaisessa valossa.

**Testi 3:**

Robotti on ohjelmoitu skannaamaan ympärillään olevat esteet kiertämällä 360 astetta itsensä ympäri ja kirjoittamaan kohteiden sijainnit muistiin. Testissää robotin tulee palata näihin sijainteihin siinä järjestyksessä, mitkä kohteet ovat lähimpinä.

Testi ei onnistunut, robotti löytää kohteet ja kirjaa niiden astekulmat ja etäisyydet ylös. Robotin kääntyminen ei kuitenkaan ole riittävän täsmällistä ja robotilla on vaikeuksia löytää kaikki kohteet uudelleen. Yritin korjata tilannetta asettamalla robotille ohjeet joiden avulla se voi etsiä kohteen uudelleen, mutta tämäkin johti väärien kohteiden löytymiseen jos robotti kääntyy alunperin merkittävästi väärän määrän.

Testin perusteella päätin muuttaa tavan jolla robotti etsii kohteet. Se tulee jatkossa käymään tutkimassa kohteet saman tien ne ensimmäisen kerran nähdessään. Näin moottorien epätarkkuus ei ole yhtä suuri ongelma.

**Testi 4:**

Robotti siirtyy kohteiden eteen nyt heti ne havaitessan. Testin tarkoituksena on selvittää kuinka hyvin robotti pystyy parkkeeraamaan kohteiden  eteen ja tunnistamaan niiden värin. Tällä kertaa testit onnistui niin hyvin kuin värien erottelu sensorin aiemman testauksen perusteella osasi odottaa. Robotilla on yhä taipumus tunnistaa värejä ruskeaksi, mutta parkkeeraus kohteen eteen ja värin tunnistaminen toimii hyvin.

Testin perusteella tunnistus toimii parhaiten, mikäli tuulimyllyjen lavat ovat + asennossa x asennon sijasta. Silloin lapa on oikealla korkeudella jotta värisensori pystyy lukemaan värin myös siitä eikä robotti aja kohteen ohi.

Rajoitukset ja tulevaisuus
--------------------------

Robotti tunnistaa ainoastaan kohteita jotka ovat riittävän korkeita. IR sensori on kiinnitettynä robotin päähän niin että se on hiukan yläkenossa. Mitä kauempana kohde on sen korkeampi sen tulee olla. Projektissa käytwttyjen tuulimyllyjen korkeus on noin 15 cm, mutta paljon tätä matalammat kohteet jää sensorin alapuolelle. Tätä voisi korjata suunnittelemalla pään rakenne eri tavoin niin että sensori osoittaisi alemmas.

Robotti odottaa kohteiden olevan ainakin muutaman senttimetrin levyisiä. Kun kohde tunnistetaan, robotti korjaa asentoaan jotta suuntaus olisi keskelle kohdetta. Jos kohde on liian kapea, hypätään tässä kohteen ohi... Leveydellä ei sen sijaan ole ylämittaa, vaan kohde voi olla kuinka leveä tahansa ja se tunnistetaan yhdeksi kohteeksi. Alun korjauksen voi poistaa, mikäli kohde on suorakulmion muotoinen, (se on lisätty tuulimyllyn lapojen takia) jolloin myös kapeampien kohteiden tunnistamisen pitäisi sujua paremmin.

Jatkokehittelyssä robotin liikkumista voisi yrittää säätää vähemmän katkonaiseksi. Myös värien tunnistusta useammilla erilaisilla materiaaleilla voisi testata parhaan tunnistettavuuden löytämiseksi, sekä erilaisia valaistusoloja, joilla ruskeita tunnistukisa voisi minimoida.

Käyttöohje
----------

Kokoa robotti [rakennusohjeen](/docs/rakennusohje) mukaisesti ja aja Main.java ohjelma lejOs EV3 ohjelmana. Jos ohjelman asettaa robotin valikosta oletusohjelmaksi, se käynnistyy uudelleen keskimmäistä nappia painamalla. Ohjelman voi keskeyttää kesken suorituksen painamalla samaan aikaan keskimmäistä ja alinta nappia.

Sijoita robotti keskelle huonetta niin että tilaa on reilusti joka suuntaan. Laita päälle kaikki valot, silloin värisensori toimii parhaiten.

Sijoita rakentamasi tuulimyllyt tai muut värikkäät kohteet robotin ympärille. Sopiva etäisyys on noin 10-40cm. Tuulimyllyjen välissä tulee olla ainakin pieni väli, jotta ne tunnistetaan erillisiksi kohteiksi, mutta muuten sijoittelulla ei ole merkitystä.

Käynnstä robotti painamalla keskimmäistä nappia.

Robotin kierrettyä 360 asteen kierros, se pysähtyy ja konsoliin syttyy punainen ledvalo merkiki ohjelman päättymisestä. Näytöllä näkyy lista havaituista kohteista ja värit joiksi ne on tunnistettu. Keskimmäsitä nappia painamalla ohjelma loppuu.