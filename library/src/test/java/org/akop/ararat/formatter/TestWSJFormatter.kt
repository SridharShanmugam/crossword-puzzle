// Copyright (c) Akop Karapetyan
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package org.akop.ararat.formatter

import org.akop.ararat.io.WSJFormatter
import org.akop.ararat.io.WSJFormatter.Companion.PUBLISH_DATE_FORMAT
import org.junit.Test


class TestWSJFormatter: BaseTest() {

    @Test
    fun crossword_test() {
        proofs.forEach { p ->
            println("${p.filePath}: ")
            WSJFormatter().load(p.filePath).also { cw -> assertProof(cw, p) }
        }
    }

    companion object {
        val proofs = arrayOf(
                Proof(
                        filePath = "wsj.json",
                        metadata = Metadata(
                                width = 15,
                                height = 15,
                                squareCount = 185,
                                title = "That Smarts!",
                                flags = 0,
                                description = "",
                                author = "By Dan Fisher/Edited by Mike Shenk",
                                copyright = "The Wall Street Journal",
                                comment = null,
                                date = PUBLISH_DATE_FORMAT.parse("Monday, 07 May 2018").time,
                                hash = "14f52e52fb2ad08278c92aae1962f8ca270b11ec"
                        ),
                        hints = arrayOf(
                                "1A.Block, as a river",
                                "6A.\"What a pity!\"",
                                "10A.Explosive stuff",
                                "13A.More than plump",
                                "14A.\"What ___ do for you?\"",
                                "15A.Moccasin or mule",
                                "16A.Lawn-tending machine",
                                "18A.Pupil setting",
                                "19A.Eden exile",
                                "20A.Lot buy",
                                "21A.Crowning",
                                "23A.Mao's domain",
                                "26A.\"Gladiator\" star",
                                "27A.Autographing need",
                                "28A.Minute part",
                                "30A.Spoil",
                                "33A.Harry Potter's Hedwig, for one",
                                "36A.Cattle call",
                                "37A.Dirties",
                                "39A.Pursue romantically",
                                "40A.Backwater burgs",
                                "43A.Brothers in cowls",
                                "45A.Least narrow",
                                "46A.Put a lid on",
                                "47A.\"The Importance of Being Earnest\" playwright",
                                "48A.Michigan State team",
                                "53A.Has as a tenant",
                                "55A.Painting, sculpture and the like",
                                "56A.Brit's bathroom",
                                "57A.Alan of \"M*A*S*H\"",
                                "58A.Minutely detailed, as an account",
                                "61A.Storyline",
                                "62A.\"A Death in the Family\" author James",
                                "63A.Employee's reward",
                                "64A.Dir. opposite NNE",
                                "65A.Place to employ a 16-Across",
                                "66A.Spirited horse",
                                "1D.Steroids addict",
                                "2D.Superior to",
                                "3D.Made kittenish cries",
                                "4D.Take advantage of",
                                "5D.Roosted",
                                "6D.Item in a squirrel's stash",
                                "7D.Bar focus",
                                "8D.Working without ___ (taking risks)",
                                "9D.Hot Mediterranean winds",
                                "10D.Hardcore hip-hop performance",
                                "11D.Sleep preventer",
                                "12D.Final, for one",
                                "15D.Buying or selling of church offices",
                                "17D.Primary",
                                "22D.Front of a freighter",
                                "24D.Expert in IRS rules",
                                "25D.Lends a hand",
                                "29D.In the mil. by choice",
                                "30D.Maker of Yukons and Acadias",
                                "31D.Tic-tac-toe win",
                                "32D.Projecting architectural feature",
                                "34D.Stir-frying vessel",
                                "35D.___ Angeles",
                                "37D.Shrub with aromatic leaves",
                                "38D.Clip-___ (some ties)",
                                "41D.Attack with a lance",
                                "42D.Racetrack numbers",
                                "43D.They suffer for causes",
                                "44D.Make a choice",
                                "46D.Atkins Diet no-no",
                                "47D.Water sources",
                                "49D.Handled indelicately",
                                "50D.TV oldie \"Kate & ___\"",
                                "51D.Gallows sight",
                                "52D.Planted",
                                "53D.Emulates Cardi B",
                                "54D.Gymnast Korbut",
                                "59D.\"___ the ramparts we...\"",
                                "60D.Club on a diamond"
                        ),
                        layout = arrayOf(
                                "DAMUP#ALAS##TNT",
                                "OBESE#CANI#SHOE",
                                "POWERMOWER#IRIS",
                                "EVE#CAR#TOPMOST",
                                "REDCHINA#CROWE#",
                                "###PEN#SECOND##",
                                "GOBAD##SNOWYOWL",
                                "MOO##SOILS##WOO",
                                "COWTOWNS##MONKS",
                                "##WIDEST#CAP###",
                                "#WILDE#SPARTANS",
                                "RENTSTO#ART#LOO",
                                "ALDA#BLOWBYBLOW",
                                "PLOT#AGEE#RAISE",
                                "SSW##YARD#STEED"
                        )
                ),
                Proof(
                        filePath = "wsj_htmlentities.json",
                        metadata = Metadata(
                                width = 21,
                                height = 21,
                                squareCount = 365,
                                title = "Treasure Hunt",
                                flags = 0,
                                description = "",
                                author = "by Mike Shenk/Edited by Mike Shenk",
                                copyright = "The Wall Street Journal",
                                comment = null,
                                date = PUBLISH_DATE_FORMAT.parse("Saturday, 28 September 2019").time,
                                hash = "14e6595b021e7fe4fdc4e71b5740740d2c18ded8"
                        ),
                        hints = arrayOf(
                                "1A.Groenlandia, por ejemplo",
                                "5A.Sister of Chekhov’s Masha and Irina",
                                "9A.Move, in realty jargon",
                                "13A.Maker of Predator laptops",
                                "17A.Sentence subject, usually",
                                "18A.Male gamete",
                                "19A.Dickerson and Dane",
                                "21A.Turbine part",
                                "22A.Treasure hunt instruction, part 1",
                                "26A.Game played with an odd number of cards",
                                "27A.Fool",
                                "28A.Iago plants Desdemona’s handkerchief in his lodgings",
                                "29A.Corrida cries",
                                "30A.Flooring option",
                                "31A.Numbered rd.",
                                "32A.Braves, on scoreboards",
                                "33A.Ovid’s first work",
                                "36A.Rip off",
                                "38A.Aromatic neckwear",
                                "39A.Reuben base",
                                "40A.Horizontal lines in graphing",
                                "41A.Insulting allegations",
                                "42A.Explorer who laid the groundwork for the British claim to Canada",
                                "44A.Thread target",
                                "45A.Game leaders",
                                "47A.Blew a gasket",
                                "52A.Home field of the Eagles, familiarly, with “the”",
                                "54A.Magazine contents",
                                "55A.Woodland walkway",
                                "56A.“...upon the stair, ___ man who wasn’t there”",
                                "57A.Tom Joad, e.g.",
                                "59A.Starchy sort",
                                "60A.Showed extreme disrespect toward",
                                "61A.Treasure hunt instruction, part 2",
                                "67A.Early milestone",
                                "68A.Secluded spots",
                                "69A.Satisfy a nicotine craving, perhaps",
                                "70A.Done in",
                                "71A.Reeve’s “Deathtrap” co-star",
                                "72A.Be mindful of",
                                "74A.Shoebox number",
                                "78A.Boisterous activity",
                                "80A.Bird that catches fish by plunge-diving",
                                "82A.Direct the crew",
                                "83A.They’re sometimes topped with tzatziki",
                                "84A.Capital of a continent",
                                "85A.Ultraviolet blocker",
                                "87A.Sin reciprocal, in trig",
                                "90A.Big marble",
                                "91A.Does some dieting",
                                "92A.Hits bottom?",
                                "93A.Blood-typing letters",
                                "94A.Clunker",
                                "95A.Hacienda room",
                                "96A.Minor complaint",
                                "97A.Farm worker who helps ewes deliver",
                                "99A.Jack-in-the-box part",
                                "101A.Person who might take a tumble",
                                "105A.Treasure hunt instruction, part 3",
                                "108A.Black-and-white mammal",
                                "109A.Red Sea borderer",
                                "110A.Casino array",
                                "111A.Greek vowels",
                                "112A.Overly curious",
                                "113A.Nursery bunch",
                                "114A.Diner offering",
                                "115A.Minimal change",
                                "1D.Scoop",
                                "2D.Agronomy focus",
                                "3D.Ilsa’s surname in “Casablanca”",
                                "4D.Wrap-up phrase in a compilation record commercial ",
                                "5D.Pieces by pundits",
                                "6D.Cariou of “Blue Bloods”",
                                "7D.Mortarboard flinger",
                                "8D.Current checkers",
                                "9D.Upshot",
                                "10D.Slowly destroy",
                                "11D.Cereal Mikey liked in a ’70s ad",
                                "12D.When the MLB series is played",
                                "13D.Means: Abbr.",
                                "14D.Hail fellow?",
                                "15D.Deep-seated hate",
                                "16D.Fix a flat, say",
                                "18D.Starts in fear",
                                "20D.Strings, e.g.",
                                "23D.“Unto the Sons” memoirist Gay",
                                "24D.2019 NHL Rookie of the Year Pettersson",
                                "25D.Highlands denial",
                                "31D.Defiant sort",
                                "33D.Skater’s spinning jump",
                                "34D.Request for permission",
                                "35D.Farm team",
                                "36D.Mr. Miniver, in “Mrs. Miniver”",
                                "37D.Colors",
                                "38D.Toward midnight",
                                "41D.It has a head and a thread",
                                "42D.Progress with minimal effort",
                                "43D.Cause to goof",
                                "46D.Senator King’s state",
                                "47D.Expresses amusement",
                                "48D.Stuns",
                                "49D.“The Girl From Ipanema” saxophonist",
                                "50D.Series-ending abbr.",
                                "51D.Check line",
                                "53D.Fish product used by tanners",
                                "55D.Tyke’s transport",
                                "58D.“Songbird” saxophonist",
                                "59D.Spurious",
                                "60D.Garden tool",
                                "61D.Diner offering",
                                "62D.Inuit for “house”",
                                "63D.Cherished",
                                "64D.Lined up",
                                "65D.Grinds away",
                                "66D.Ties up",
                                "71D.Band-Aid rival",
                                "72D.Injury",
                                "73D.Grandson of Eve",
                                "75D.Representative symbol",
                                "76D.Pass (out)",
                                "77D.Past partners",
                                "79D.Alternately",
                                "80D.From northern South America",
                                "81D.Boozehounds",
                                "84D.“Black-ish” co-star Tracee ___ Ross",
                                "86D.Pre-Columbian civilization of Oaxaca",
                                "87D.Brand with “Take me away!” ads",
                                "88D.Pizza chain in many malls and airports",
                                "89D.Newspaper feature",
                                "91D.Famed Theater District restaurant",
                                "92D.Drei doubled",
                                "94D.Agnus ___",
                                "95D.Herring family fish",
                                "96D.Ententes",
                                "98D.Present time, briefly",
                                "99D.Prefix with gram or buoy",
                                "100D.Glitzy do",
                                "102D.Memory unit",
                                "103D.Bean in space",
                                "104D.Clinical study",
                                "106D.Acumen",
                                "107D.Balderdash"
                        ),
                        layout = arrayOf(
                                "ISLA##OLGA#RELO##ACER",
                                "NOUN#SPERM#ERICS#VANE",
                                "FINDTHENAMESOFTENGEMS",
                                "OLDMAID#DELUDE#CASSIO",
                                "###OLES##TILE#RTE#ATL",
                                "AMORES#CHEAT#LEI##RYE",
                                "XAXES#SLURS#CABOT####",
                                "EYE#EMCEES#GOTENRAGED",
                                "LINC#ARMS#TRAIL#IMETA",
                                "###OKIE##PRISS#SPATAT",
                                "HIDDENWITHINTHEPUZZLE",
                                "AGEONE#NOOKS##VAPE###",
                                "SLAIN#CAINE#HEED#SIZE",
                                "HURLYBURLY#GANNET#COX",
                                "####GYROS#EUROS#OZONE",
                                "CSC##TAW#SLIMS#SPANKS",
                                "ABO#DUD#SALA##PEEP###",
                                "LAMBER#SPRING#ACROBAT",
                                "GRIDINWORDSEARCHSTYLE",
                                "ORCA#SINAI#SLOTS#ETAS",
                                "NOSY##TOTS#EATS##CENT"
                        )
                )
        )
    }
}
