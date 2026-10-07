package es.uvigo.esei.aed2.activity11;

/*-
 * #%L
 * AEDII - Activities
 * %%
 * Copyright (C) 2025 - 2026 Rosalía Laza Fidalgo, María Reyes Pavón Rial,
 * Florentino Fernández Riverola, María Novo Lourés, and Miguel Reboiro Jato
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import static es.uvigo.esei.aed2.activity8.data.IsEqualToMap.equalToMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import es.uvigo.esei.aed2.activity11.data.DynamicProgrammingRepository;
import es.uvigo.esei.aed2.map.Map;

public class DynamicProgrammingAlgorithmTestCase {

  private final DynamicProgrammingRepository dataPD = new DynamicProgrammingRepository();


  /**
   * Test of giveChangeDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testGiveChangeDynamic() {
    Map<Integer, Integer> expectedMap = this.dataPD.getMapChangeResult();

    Map<Integer, Integer> resultMap = DynamicProgrammingAlgorithm.giveChangeDynamic(1200, this.dataPD.getMapChange());

    assertThat(resultMap, is(equalToMap(expectedMap)));
  }

  /**
   * Test of fillRucksackDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testFillRucksackDynamic() {
    List<String> result = DynamicProgrammingAlgorithm.fillRucksackDynamic(9,
        this.dataPD.getMapRucksackVolumes(),
        this.dataPD.getMapRucksackWeights());

    assertThat(result, is(this.dataPD.getListRucksack()));
  }

  /**
   * Test of fibionacciDinamic method, of class DinamicProgramingAlgorithm.
   */
  @Test
  public void testFibonacciDynamic() {
    assertThat(DynamicProgrammingAlgorithm.fibonacciDynamic(10), is(55));
  }
}
