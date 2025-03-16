package humanextended.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.powers.GarbageCollectorPower2;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;


public class GarbageCollector extends BaseCard {

    public static final String ID = theHuman.cards.GarbageCollector.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            3);

    public GarbageCollector() {
        super(ID, info, "theHumanResources/images/cards/GarbageCollector.png");
        setMagic(1, 1);
        setCostUpgrade(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new GarbageCollectorPower2(magicNumber));
    }

}

