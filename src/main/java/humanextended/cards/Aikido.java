package humanextended.cards;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.characters.TheHuman;
import theHuman.powers.TechniquePower;

import java.util.Collections;
import java.util.List;

import static humanextended.HumanExtendedMod.imagePath;
import static theHuman.HumanMod.getModID;

// renamed from Ageing
public class Aikido extends BaseCard {

    public static final String ID = theHuman.cards.Ageing.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(getModID() + ":TechniqueWords");
    private static final TooltipInfo toolTipInfo = new TooltipInfo("[#a83277]" + uiStrings.TEXT[0], uiStrings.TEXT[1]);

    public Aikido() {
        super(ID, info, imagePath("cards/skill/Aikido.png"));
        setExhaust(true);
        setMagic(2, 1);
    }


    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Collections.singletonList(toolTipInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Wiz.atb(new AbstractGameAction() {

            @Override
            public void update() {
                if (m != null && m.getIntentBaseDmg() >= 0) {
                    Wiz.applyToSelfTop(new DexterityPower(p, magicNumber));
                } else {
                    Wiz.applyToEnemyTop(m, new StrengthPower(m, -magicNumber));
                }

                isDone = true;
            }
        });

        if (p.hasPower(TechniquePower.POWER_ID)) {
            Wiz.atb(new MakeTempCardInDiscardAction(this, 1));
        }

        Wiz.applyToSelf(new TechniquePower(p, p, 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = Wiz.p().hasPower(TechniquePower.POWER_ID) ? Color.valueOf("a83277") : AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

}
