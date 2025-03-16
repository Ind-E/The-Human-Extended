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

import static humanextended.HumanExtendedMod.imagePath;

@AutoAdd.Ignore
public class Lollipop extends BaseCard {

    public static final String ID = makeID("Lollipop");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            0);

    public Lollipop() {
        super(ID, info, imagePath("cards/skill/HealthyEating.png"));
        setBlock(5, 3);
        setMagic(2);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ReducePowerAction(p, p, HealthinessPower.POWER_ID, magicNumber));
        Wiz.doBlk(block);
    }
}