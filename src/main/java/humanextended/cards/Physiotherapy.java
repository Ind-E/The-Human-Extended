package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;

import static humanextended.HumanExtendedMod.imagePath;


public class Physiotherapy extends BaseCard {

    public static final String ID = theHuman.cards.Physiotherapy.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public Physiotherapy() {
        super(ID, info, "theHumanResources/images/cards/Physiotherapy.png");
        setBlock(24, 6);
        setMagic(2);
        setSelfRetain(false, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ExhaustAction(3, false, false, false));
        Wiz.applyToSelf(new HealthinessPower(p, p, magicNumber));
        Wiz.doBlk(block);
    }
}
