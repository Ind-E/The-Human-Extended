package humanextended.cards;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;
import theHuman.powers.PeacefulPower;

public class HealthyEating extends BaseCard {
    private static final int HEALTHINESS = 5;
    private static final int PEACEFUL_LOSS = 3;
    public static final String ID = makeID("HealthyEating");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2);

    public HealthyEating() {
        super(ID, info);
        setMagic(HEALTHINESS);
        setCustomVar("PEACEFUL_LOSS", PEACEFUL_LOSS);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new HealthinessPower(p, p, magicNumber));
        Wiz.atb(new ReducePowerAction(p, p, PeacefulPower.POWER_ID, customVar("PEACEFUL_LOSS")));
    }

}
