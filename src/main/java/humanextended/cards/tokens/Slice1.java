package humanextended.cards.tokens;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.cards.BaseCard;
import humanextended.powers.Slice1Power;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;

import static humanextended.HumanExtendedMod.imagePath;

@AutoAdd.Ignore
public class Slice1 extends BaseCard {

    public static final String ID = makeID("Slice1");
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.NONE,
            -2);

    public Slice1() {
        super(ID, info, imagePath("cards/skill/Slice1.png"));
        setMagic(15, 5);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new Slice1Power(magicNumber));
    }

    @Override
    public void onChoseThisOption() {
        use(Wiz.p(), null);
    }
}
