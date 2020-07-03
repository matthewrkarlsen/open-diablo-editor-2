/*
 * This code is from 'Open Diablo Editor' version 2.x, authored by Matthew R. Karlsen and other contributors.
 *
 * See README.txt for full contributor list.
 *
 * This code is tri-licensed, under the CC0, MIT and Apache 2.0 licenses (i.e. pick one of the three licenses).
 *
 * Careful attribution is strongly preferred, though not required under CC0.
 */
package org.d1ablo.ode.factories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.undertow.Undertow;
import io.undertow.server.handlers.BlockingHandler;
import io.undertow.server.handlers.GracefulShutdownHandler;
import io.undertow.server.handlers.PathHandler;
import org.d1ablo.ode.bintool.BinEditTool;
import org.d1ablo.ode.server.ODEServer;
import org.d1ablo.ode.server.ResourceProvider;
import org.d1ablo.ode.server.handler.*;
import org.d1ablo.ode.stores.*;

import java.util.ArrayList;

public class ServerFactory {

    public ODEServer constructServer() {
        System.out.println("Constructing ODE server.");

        BinEditTool binEditTool = new BinEditTool();
        QuestFactory questFactory = new QuestFactory(binEditTool);
        SpellFactory spellFactory = new SpellFactory(binEditTool);
        ShrineFactory shrineFactory = new ShrineFactory();
        ItemModifierFactory itemModifierFactory = new ItemModifierFactory(binEditTool);
        UniqueItemFactory uniqueItemFactory = new UniqueItemFactory(binEditTool);
        CharacterFactory characterFactory = new CharacterFactory(binEditTool);
        BaseItemFactory baseItemFactory = new BaseItemFactory(binEditTool);
        BaseMonsterFactory baseMonsterFactory = new BaseMonsterFactory(binEditTool);
        UniqueMonsterFactory uniqueMonsterFactory = new UniqueMonsterFactory(binEditTool);

        CompleteStore completeStore = new CompleteStore(
                new QuestStore(new ArrayList<>(), questFactory, questFactory.getDummyQuest()),
                new SpellsStore(new ArrayList<>(), spellFactory, spellFactory.getDummySpell()),
                new ShrinesStore(new ArrayList<>(), binEditTool, shrineFactory, shrineFactory.getDummyShrine()),
                new ItemModifiersStore(new ArrayList<>(), itemModifierFactory, itemModifierFactory.getDummyItemModifier()),
                new UniqueItemStore(new ArrayList<>(), uniqueItemFactory, uniqueItemFactory.getDummyUniqueItem()),
                new CharacterStore(new ArrayList<>(), binEditTool, characterFactory, characterFactory.getDummyCharacter()),
                new BaseItemStore(new ArrayList<>(), baseItemFactory, baseItemFactory.getDummyBaseItem()),
                new BaseMonsterStore(new ArrayList<>(), baseMonsterFactory, baseMonsterFactory.getDummyBaseMonster()),
                new UniqueMonsterStore(new ArrayList<>(), uniqueMonsterFactory, uniqueMonsterFactory.getDummyUniqueMonster())
        );

        InjectableValues injectableValues = new InjectableValues.Std()
                .addValue(BinEditTool.class, binEditTool)
                .addValue(QuestFactory.class, questFactory)
                .addValue(SpellFactory.class, spellFactory)
                .addValue(ShrineFactory.class, shrineFactory)
                .addValue(ItemModifierFactory.class, itemModifierFactory)
                .addValue(UniqueItemFactory.class, uniqueItemFactory)
                .addValue(CharacterFactory.class, characterFactory)
                .addValue(BaseItemFactory.class, baseItemFactory)
                .addValue(BaseMonsterFactory.class, baseMonsterFactory)
                .addValue(UniqueMonsterFactory.class, uniqueMonsterFactory);

        JsonMapper writingJsonMapper = new JsonMapper();

        JsonMapper readingJsonMapper = new JsonMapper();
        readingJsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        readingJsonMapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        readingJsonMapper.setInjectableValues(injectableValues);

        DataHandler dataHandler = new DataHandler(completeStore,
                writingJsonMapper, readingJsonMapper);

        ODEServer odeServer = new ODEServer();
        ResourceProvider resourceProvider = new ResourceProvider();
        BinaryHandler binaryHandler = new BinaryHandler(completeStore, odeServer);

        //TODO -- see if RoutingHandler can simplify this code and handler code
        GracefulShutdownHandler mainRootHandler = new GracefulShutdownHandler(
                new PathHandler()
                        .addExactPath("favicon.ico", new FaviconHandler(resourceProvider))
                        .addPrefixPath("scripts", new ScriptsHandler())
                        .addExactPath("style/ode.css", new CssHandler())
                        .addPrefixPath("binary", new BlockingHandler(binaryHandler))
                        .addExactPath("/loaded", new StatusHandler(odeServer))
                        .addPrefixPath("data", new BlockingHandler(dataHandler))
                        .addPrefixPath("/", new BasicPathHandler(resourceProvider))
                        .addExactPath("/control/shutdown", new ShutdownHandler(odeServer, resourceProvider))
        );
        Undertow undertow = Undertow.builder()
                .addHttpListener(4666, "localhost", mainRootHandler)
                .build();
        odeServer.setUndertow(undertow); //FIXME -- is there a better way to do this?
        return odeServer;
    }
}
