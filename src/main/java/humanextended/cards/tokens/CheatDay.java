package humanextended.cards.tokens;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.BaseCard;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;
import theHuman.powers.PeacefulPower;

import static humanextended.HumanExtendedMod.imagePath;

@AutoAdd.Ignore
public class CheatDay extends BaseCard {

    public static final String ID = makeID("CheatDay");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            1);

    public CheatDay() {
        super(ID, info, imagePath("cards/skill/CheatDay.png"));
        setMagic(10, 2);
        setCustomVar("HEALTHINESS_LOSS", 5);
        setCostUpgrade(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PeacefulPower(p, p, magicNumber));
        Wiz.atb(new ReducePowerAction(p, p, HealthinessPower.POWER_ID, customVar("HEALTHINESS_LOSS")));
    }

}
