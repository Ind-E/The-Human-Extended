package humanextended.cards.tokens;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.actions.EasyModalChoiceAction;
import humanextended.cards.BaseCard;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.cards.Backpack;
import theHuman.characters.TheHuman;
import theHuman.powers.HealthinessPower;

import java.util.ArrayList;

import static humanextended.HumanExtendedMod.imagePath;

@AutoAdd.Ignore
public class SliceOfCake extends BaseCard {

    public static final String ID = makeID("SliceOfCake");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            1);

    public SliceOfCake() {
        super(ID, info, imagePath("cards/skill/SliceOfCake.png"));
        setMagic(5);
        setExhaust(true);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ReducePowerAction(p, p, HealthinessPower.POWER_ID, magicNumber));

        ArrayList<AbstractCard> wishes = new ArrayList<>();
        wishes.add(new Slice1());
        wishes.add(new Slice2());
        wishes.add(new Slice3());

        Wiz.atb(new EasyModalChoiceAction(wishes));
    }
}

