package humanextended.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import humanextended.util.CardStats;
import humanextended.util.Wiz;
import theHuman.HumanMod;
import theHuman.characters.TheHuman;

import java.util.Iterator;

import static humanextended.HumanExtendedMod.imagePath;

public class TrashToTreasure extends BaseCard {

    public static final String ID = theHuman.cards.TrashToTreasure.ID;
    private static final CardStats info = new CardStats(
            TheHuman.Enums.COLOR_SKIN,
            AbstractCard.CardType.SKILL,
            AbstractCard.CardRarity.UNCOMMON,
            AbstractCard.CardTarget.SELF,
            1);

    public TrashToTreasure() {
        super(ID, info, "theHumanResources/images/cards/TrashToTreasure.png");
        setExhaust(true);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {

            @Override
            public void update() {
                Iterator<AbstractCard> cards = p.hand.group.iterator();

                while (cards.hasNext()) {
                    AbstractCard c = cards.next();
                    if (c.tags.contains(HumanMod.HumanCardTags.JUNK_HUMAN)) {
                        cards.remove();
                        Wiz.att(new GainEnergyAction(1));
                        p.hand.moveToExhaustPile(c);
                        c.triggerOnExhaust();
                    }
                }

                isDone = true;
            }
        });
    }


}
