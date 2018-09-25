package service.impl;

import service.AnalyzeService;
import utils.Analyze;

/**
 * @program: DrawingBoard
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-09-23 17:52
 **/
public class AnalyzeServiceImpl implements AnalyzeService {

    public String getMark(int strokes) {
        for (Analyze analyze : Analyze.values()) {
            if (analyze.strokes == strokes) {
                return analyze.mark;
            }
        }
        return Analyze.POLYGON.mark;
    }
}
