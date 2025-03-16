package humanextended.cards;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.cards.BackPain;
import theHuman.characters.TheHuman;
import theHuman.patches.subscribers.OnShuffleSubscriber;

import java.util.Collections;
import java.util.List;

import static humanextended.HumanExtendedMod.imagePath;
import static theHuman.HumanMod.getModID;

// renamed from Back Pain
public class Reevaluate extends BaseCard {

    public static final String ID = HumanMod.makeID(BackPain.class.getSimpleName());
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.NONE,
            0);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(getModID() + ":LifestyleWords");
    private static final TooltipInfo toolTipInfo = new TooltipInfo("[#2000a7]" + uiStrings.TEXT[0], uiStrings.TEXT[1]);

    public Reevaluate() {
        super(ID, info, imagePath("cards/skill/Reevaluate.png"));
        setMagic(4);
        setSelfRetain(false, true);
        setExhaust(true, false);
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(toolTipInfo);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        }
        super.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DiscardAction(p, p, p.hand.size(), false));
        Wiz.atb(new DrawCardAction(magicNumber));
        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof OnShuffleSubscriber) {
                ((OnShuffleSubscriber) c).onShuffle();
            }
        }
        if (upgraded) {
            for (AbstractCard c : p.drawPile.group) {
                if (c instanceof OnShuffleSubscriber) {
                    ((OnShuffleSubscriber) c).onShuffle();
                }
            }
        }
    }
}

