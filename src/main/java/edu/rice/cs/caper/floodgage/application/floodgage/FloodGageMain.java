/*
Copyright 2017 Rice University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package edu.rice.cs.caper.floodgage.application.floodgage;
import edu.rice.cs.caper.floodgage.application.floodgage.synthesizer.SynthesizerBayou_1_1_0;
import edu.rice.cs.caper.floodgage.application.floodgage.view.ViewConsole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.status.StatusLogger;

/**
 * The entry point class for the Floodgage tool.
 */
public class FloodGageMain
{
    public static void main(String[] args) throws Exception
    {
        // some of the libs we depend on hard coded Log4J into the source. So disable their logging
        // to prevent warnings from being printed to the console
        StatusLogger.getLogger().setLevel(Level.OFF);

        if(args.length != 1)
        {
            System.out.println("Usage: java -cp [trails.jar]:[floodgage.jar] [bayou-hostname]");
            return;
        }

        String bayouHostname = args[0];
        SynthesizerBayou_1_1_0 synthesizer = new SynthesizerBayou_1_1_0(bayouHostname, 8080);

        try
        {
            new FloodGage().run(synthesizer, new ViewConsole());
        }
        finally
        {
            System.out.println(""); // so that visually the command prompt on exit is not cluttered by our output
        }
    }
}
