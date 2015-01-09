Robottiohjelmoinnin harjoitustyö: Don Quijote
=============================================

Joulu 2014
Verna Koskinen
014427857

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