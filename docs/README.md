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