package humanextended.cards.tokens;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.BaseCard;
import humanextended.powers.Slice2Power;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;

@AutoAdd.Ignore
public class Slice2 extends BaseCard {

    public static final String ID = makeID("Slice2");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public Slice2() {
        super(ID, info, imagePath("cards/skill/Slice2.png"));
        setMagic(10, 2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new Slice2Power(magicNumber));
    }

    @Override
    public void onChoseThisOption() {
        use(Wiz.p(), null);
    }
}
