package First;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class First {

    public static void main(String[] args) {
        System.out.printf("The amount of increasing items is in part one: %d\n", partOne());
        System.out.printf("The amount of increasing items is in part two: %d\n\n", partTwo());
    }

    private static int partOne() {
        Integer[] report = getReportData();
        return calculateIncreases(report);
    }

    private static int partTwo() {
        int offset = 0;
        List<Integer> sums = new ArrayList<>();
        List<Integer> report = Arrays.asList(getReportData());
        while (report.size() >= (offset + 3)) {
            List<Integer> sublist = report.subList(offset, offset + 3);
            sums.add(sublist.stream().reduce(0, Integer::sum));
            offset++;
        }

        return calculateIncreases(sums.toArray(new Integer[0]));
    }

    private static int calculateIncreases(Integer[] report) {
        int increases = 0;
        for (int i = 0; i < report.length; i++) {
            if (i == 0) {
                continue;
            }

            if (report[i] > report[i - 1]) {
                increases++;
            }
        }
        return increases;
    }

    private static Integer[] getReportData() {
        return new Integer[]{171, 173, 174, 163, 161, 157, 156, 154, 152, 156, 151, 153, 132, 135, 151, 143, 141, 149, 145, 147, 142, 143, 139, 141, 144, 147, 137, 144, 147, 153, 151, 153, 157, 185, 186, 185, 181, 161, 177, 179, 177, 178, 173, 175, 183, 181, 191, 189, 186, 189, 192, 191, 189, 199, 208, 218, 216, 210, 209, 208, 215, 207, 198, 202, 204, 205, 204, 203, 205, 207, 208, 209, 218, 234, 231, 245, 244, 243, 265, 264, 279, 282, 283, 274, 276, 279, 281, 279, 258, 256, 289, 297, 300, 304, 308, 298, 302, 317, 316, 315, 314, 296, 298, 301, 298, 293, 300, 293, 290, 286, 282, 278, 280, 283, 292, 297, 315, 323, 324, 321, 324, 332, 334, 366, 375, 382, 388, 395, 400, 410, 405, 407, 408, 405, 403, 426, 425, 423, 440, 444, 469, 471, 472, 498, 501, 498, 499, 500, 502, 514, 515, 516, 518, 517, 516, 507, 514, 521, 523, 521, 513, 518, 531, 543, 546, 540, 528, 529, 520, 530, 533, 534, 532, 535, 538, 545, 550, 548, 534, 552, 555, 570, 571, 589, 595, 594, 598, 594, 597, 585, 568, 558, 545, 549, 543, 544, 548, 528, 533, 511, 510, 511, 504, 505, 510, 511, 514, 486, 506, 508, 474, 475, 485, 501, 498, 499, 500, 504, 502, 510, 507, 512, 508, 515, 517, 521, 537, 536, 532, 533, 532, 531, 535, 547, 558, 547, 546, 573, 591, 593, 576, 577, 580, 581, 588, 595, 594, 589, 586, 600, 602, 594, 593, 598, 586, 587, 583, 571, 564, 561, 562, 559, 569, 568, 547, 551, 554, 565, 575, 574, 573, 568, 567, 570, 578, 575, 573, 555, 557, 571, 572, 579, 593, 609, 611, 612, 614, 613, 614, 616, 615, 616, 610, 621, 626, 620, 621, 620, 622, 623, 627, 625, 628, 618, 615, 606, 597, 602, 603, 601, 624, 623, 621, 633, 643, 638, 641, 642, 641, 643, 633, 632, 642, 649, 636, 642, 662, 670, 683, 702, 712, 713, 714, 720, 725, 726, 724, 723, 722, 691, 692, 693, 674, 671, 664, 665, 678, 681, 682, 680, 681, 693, 694, 691, 693, 694, 691, 692, 691, 692, 691, 692, 693, 684, 686, 706, 705, 696, 683, 688, 698, 701, 700, 708, 691, 689, 688, 693, 680, 681, 683, 701, 709, 715, 716, 706, 686, 683, 676, 680, 681, 687, 676, 691, 705, 702, 703, 704, 709, 713, 716, 717, 715, 716, 715, 703, 714, 720, 708, 692, 665, 654, 664, 663, 649, 652, 676, 666, 682, 683, 687, 679, 680, 699, 706, 730, 729, 728, 732, 733, 727, 728, 738, 741, 744, 742, 722, 725, 722, 716, 717, 713, 712, 703, 704, 705, 699, 700, 706, 709, 692, 693, 688, 689, 698, 695, 696, 700, 701, 688, 689, 696, 678, 669, 674, 675, 663, 664, 675, 662, 667, 671, 679, 683, 682, 716, 717, 720, 724, 737, 727, 737, 739, 716, 733, 728, 723, 741, 743, 732, 727, 729, 732, 724, 721, 717, 716, 730, 743, 742, 740, 743, 734, 735, 733, 734, 747, 741, 713, 721, 719, 743, 745, 768, 769, 775, 774, 773, 785, 789, 788, 784, 773, 809, 808, 807, 808, 809, 806, 790, 785, 806, 811, 809, 812, 814, 817, 821, 822, 823, 829, 826, 827, 828, 830, 832, 827, 828, 827, 828, 843, 844, 843, 842, 829, 830, 834, 841, 843, 863, 858, 867, 871, 888, 890, 873, 874, 860, 874, 871, 877, 878, 877, 885, 886, 893, 888, 887, 888, 889, 876, 871, 855, 850, 851, 853, 850, 855, 868, 870, 871, 886, 896, 878, 882, 893, 867, 862, 860, 861, 862, 859, 871, 875, 874, 870, 864, 867, 864, 867, 868, 872, 880, 866, 867, 874, 878, 864, 872, 870, 859, 852, 857, 869, 872, 876, 882, 894, 872, 873, 860, 861, 862, 858, 859, 865, 858, 834, 830, 801, 804, 799, 802, 804, 800, 776, 779, 790, 793, 796, 800, 804, 803, 799, 790, 788, 783, 785, 807, 802, 783, 796, 808, 782, 778, 779, 794, 800, 809, 821, 828, 826, 827, 835, 836, 840, 857, 855, 859, 870, 861, 838, 837, 843, 823, 821, 812, 811, 827, 828, 818, 819, 811, 813, 799, 801, 784, 788, 793, 797, 803, 785, 786, 785, 774, 776, 777, 778, 776, 774, 775, 778, 779, 772, 776, 774, 773, 777, 778, 779, 777, 781, 769, 760, 746, 752, 748, 754, 758, 768, 786, 784, 783, 769, 770, 782, 796, 785, 774, 775, 781, 779, 780, 769, 757, 758, 774, 790, 792, 795, 805, 804, 803, 802, 792, 795, 800, 801, 796, 797, 790, 791, 797, 801, 797, 807, 801, 796, 799, 794, 783, 759, 784, 809, 810, 824, 815, 812, 845, 846, 848, 849, 869, 868, 886, 888, 896, 904, 902, 905, 900, 907, 912, 890, 891, 892, 893, 911, 912, 916, 924, 926, 897, 885, 886, 881, 878, 879, 908, 907, 901, 902, 919, 915, 905, 906, 941, 939, 944, 945, 946, 939, 936, 952, 951, 963, 958, 972, 1000, 1017, 1003, 1002, 1004, 1005, 990, 989, 992, 993, 987, 1003, 1004, 1003, 1006, 1011, 989, 1000, 999, 1001, 1008, 1012, 1013, 1024, 1038, 1039, 1024, 1027, 1021, 1036, 1034, 1053, 1061, 1064, 1104, 1105, 1106, 1085, 1086, 1090, 1097, 1098, 1101, 1086, 1087, 1084, 1069, 1070, 1069, 1074, 1071, 1070, 1074, 1060, 1058, 1068, 1054, 1060, 1054, 1055, 1053, 1054, 1042, 1043, 1045, 1044, 1042, 1043, 1027, 1030, 1031, 1030, 1029, 1041, 1045, 1048, 1049, 1043, 1040, 1049, 1050, 1051, 1054, 1056, 1048, 1045, 1044, 1043, 1046, 1063, 1077, 1105, 1108, 1110, 1126, 1127, 1131, 1133, 1130, 1135, 1141, 1168, 1175, 1172, 1156, 1136, 1137, 1136, 1135, 1137, 1156, 1154, 1158, 1163, 1151, 1152, 1168, 1176, 1177, 1172, 1173, 1187, 1196, 1197, 1192, 1191, 1180, 1186, 1185, 1186, 1189, 1198, 1196, 1195, 1196, 1197, 1191, 1171, 1172, 1154, 1184, 1170, 1171, 1169, 1179, 1178, 1186, 1187, 1189, 1196, 1183, 1180, 1182, 1170, 1155, 1133, 1126, 1127, 1126, 1118, 1117, 1126, 1128, 1147, 1148, 1144, 1160, 1171, 1177, 1179, 1180, 1162, 1152, 1150, 1149, 1156, 1169, 1165, 1164, 1165, 1179, 1178, 1179, 1181, 1191, 1186, 1187, 1179, 1180, 1181, 1177, 1182, 1185, 1189, 1201, 1243, 1242, 1239, 1240, 1242, 1243, 1231, 1232, 1242, 1254, 1264, 1267, 1268, 1271, 1272, 1265, 1260, 1263, 1266, 1254, 1266, 1261, 1251, 1252, 1214, 1212, 1222, 1225, 1226, 1235, 1245, 1255, 1256, 1255, 1252, 1254, 1250, 1252, 1257, 1259, 1270, 1271, 1281, 1279, 1286, 1273, 1290, 1291, 1290, 1297, 1295, 1294, 1289, 1300, 1307, 1304, 1305, 1311, 1301, 1306, 1310, 1306, 1305, 1306, 1308, 1310, 1316, 1326, 1330, 1336, 1318, 1337, 1313, 1316, 1329, 1324, 1331, 1338, 1337, 1335, 1336, 1340, 1350, 1364, 1365, 1366, 1352, 1353, 1354, 1361, 1375, 1367, 1371, 1372, 1375, 1390, 1393, 1392, 1391, 1388, 1391, 1392, 1394, 1396, 1420, 1421, 1424, 1425, 1427, 1420, 1421, 1426, 1428, 1432, 1430, 1439, 1444, 1446, 1435, 1437, 1438, 1437, 1444, 1448, 1453, 1469, 1473, 1476, 1477, 1464, 1457, 1460, 1476, 1479, 1477, 1479, 1495, 1494, 1499, 1504, 1502, 1494, 1508, 1507, 1508, 1506, 1504, 1492, 1503, 1512, 1511, 1493, 1500, 1509, 1527, 1542, 1547, 1542, 1551, 1555, 1557, 1566, 1610, 1611, 1616, 1620, 1616, 1635, 1646, 1647, 1645, 1666, 1667, 1656, 1654, 1645, 1648, 1651, 1666, 1670, 1673, 1671, 1669, 1680, 1669, 1670, 1693, 1696, 1698, 1697, 1722, 1727, 1740, 1743, 1742, 1743, 1744, 1762, 1785, 1788, 1777, 1778, 1779, 1784, 1811, 1806, 1824, 1791, 1792, 1793, 1795, 1792, 1791, 1789, 1790, 1802, 1803, 1811, 1809, 1842, 1843, 1854, 1857, 1842, 1847, 1856, 1859, 1865, 1867, 1868, 1883, 1875, 1874, 1877, 1878, 1877, 1862, 1876, 1882, 1864, 1865, 1868, 1867, 1869, 1850, 1849, 1839, 1840, 1845, 1843, 1855, 1856, 1877, 1875, 1877, 1880, 1861, 1889, 1891, 1894, 1889, 1905, 1907, 1911, 1894, 1897, 1908, 1909, 1911, 1913, 1918, 1919, 1916, 1915, 1918, 1919, 1924, 1941, 1942, 1952, 1962, 1963, 1948, 1947, 1949, 1955, 1950, 1949, 1938, 1940, 1950, 1948, 1947, 1935, 1936, 1935, 1937, 1929, 1930, 1931, 1937, 1938, 1932, 1933, 1940, 1933, 1957, 1958, 1983, 1987, 1986, 1985, 1991, 1992, 2006, 2007, 2008, 2007, 2021, 2022, 2033, 2035, 2025, 2029, 2037, 2053, 2052, 2053, 2044, 2048, 2055, 2043, 2033, 2002, 2006, 2007, 2009, 2008, 2010, 2012, 2015, 2006, 2007, 2008, 2007, 2025, 2022, 2036, 2049, 2075, 2076, 2046, 2050, 2052, 2067, 2068, 2069, 2059, 2077, 2079, 2098, 2101, 2106, 2107, 2106, 2108, 2117, 2100, 2094, 2093, 2092, 2091, 2089, 2098, 2096, 2098, 2109, 2112, 2113, 2116, 2111, 2115, 2122, 2120, 2116, 2117, 2123, 2140, 2145, 2125, 2117, 2118, 2116, 2120, 2133, 2135, 2178, 2192, 2189, 2194, 2196, 2198, 2199, 2193, 2195, 2187, 2189, 2205, 2218, 2203, 2207, 2206, 2204, 2210, 2209, 2230, 2229, 2226, 2222, 2255, 2250, 2275, 2276, 2277, 2299, 2304, 2307, 2306, 2320, 2324, 2340, 2341, 2344, 2343, 2348, 2349, 2351, 2331, 2334, 2333, 2337, 2339, 2344, 2357, 2355, 2356, 2340, 2325, 2324, 2340, 2351, 2352, 2350, 2379, 2386, 2387, 2388, 2382, 2358, 2354, 2360, 2361, 2367, 2358, 2356, 2353, 2339, 2342, 2343, 2342, 2343, 2345, 2347, 2350, 2347, 2349, 2347, 2348, 2355, 2324, 2322, 2321, 2322, 2336, 2337, 2338, 2342, 2358, 2359, 2363, 2347, 2332, 2322, 2320, 2327, 2329, 2319, 2300, 2305, 2308, 2311, 2310, 2309, 2314, 2321, 2326, 2324, 2342, 2344, 2360, 2322, 2323, 2314, 2309, 2300, 2299, 2300, 2295, 2296, 2309, 2316, 2315, 2319, 2316, 2319, 2318, 2327, 2328, 2330, 2336, 2338, 2345, 2361, 2350, 2352, 2360, 2363, 2401, 2398, 2407, 2403, 2388, 2390, 2377, 2392, 2389, 2394, 2402, 2399, 2388, 2393, 2397, 2395, 2404, 2418, 2432, 2412, 2436, 2430, 2459, 2454, 2452, 2453, 2446, 2448, 2445, 2454, 2448, 2454, 2464, 2454, 2464, 2491, 2504, 2503, 2513, 2514, 2516, 2511, 2517, 2505, 2508, 2509, 2507, 2513, 2517, 2515, 2522, 2533, 2548, 2549, 2553, 2552, 2554, 2557, 2562, 2565, 2566, 2567, 2573, 2574, 2570, 2573, 2575, 2580, 2583, 2584, 2589, 2592, 2593, 2592, 2594, 2595, 2594, 2584, 2588, 2595, 2573, 2572, 2581, 2589, 2591, 2595, 2589, 2615, 2628, 2640, 2651, 2650, 2636, 2637, 2606, 2609, 2614, 2620, 2623, 2632, 2635, 2645, 2647, 2642, 2643, 2644, 2650, 2649, 2660, 2634, 2627, 2628, 2634, 2636, 2638, 2628, 2627, 2631, 2629, 2628, 2638, 2628, 2627, 2628, 2630, 2640, 2649, 2656, 2657, 2679, 2680, 2684, 2694, 2693, 2694, 2693, 2704, 2714, 2720, 2735, 2753, 2758, 2767, 2768, 2772, 2761, 2762, 2760, 2740, 2738, 2757, 2758, 2793, 2795, 2793, 2794, 2803, 2808, 2802, 2811, 2807, 2813, 2817, 2818, 2826, 2830, 2867, 2855, 2860, 2864, 2843, 2838, 2842, 2840, 2849, 2845, 2837, 2841, 2837, 2833, 2823, 2820, 2829, 2853, 2860, 2861, 2862, 2883, 2888, 2852, 2863, 2862, 2860, 2864, 2865, 2870, 2869, 2874, 2863, 2872, 2874, 2882, 2893, 2903, 2907, 2936, 2940, 2945, 2956, 2972, 2989, 2988, 2974, 2976, 2977, 2979, 2980, 2981, 2983, 2971, 2970, 2973, 2974, 2976, 3004, 2999, 3001, 3008, 3009, 3013, 3016, 3027, 3043, 3026, 3031, 3034, 3040, 3055, 3054, 3080, 3082, 3090, 3092, 3091, 3090, 3089, 3082, 3084, 3081, 3086, 3084, 3086, 3083, 3090, 3094, 3096, 3102, 3110, 3111, 3094, 3100, 3123, 3134, 3135, 3142, 3136, 3134, 3113, 3112, 3111, 3123, 3124, 3121, 3120, 3126, 3127, 3126, 3127, 3113, 3111, 3126, 3141, 3142, 3137, 3139, 3098, 3103, 3115, 3119, 3117, 3114, 3119, 3120, 3093, 3092, 3099, 3104, 3105, 3106, 3121, 3122, 3124, 3131, 3141, 3131, 3132, 3147, 3149, 3156, 3123, 3122, 3108, 3117, 3119, 3139, 3157, 3152, 3146, 3112, 3113, 3112, 3114, 3091, 3071, 3072, 3082, 3083, 3084, 3098, 3102, 3109, 3111, 3109, 3110, 3101, 3100, 3102, 3103, 3108, 3114, 3113, 3115, 3116, 3117, 3120, 3119, 3117, 3150, 3152, 3153, 3155, 3154, 3157, 3150, 3143, 3144, 3139, 3127, 3102, 3123, 3122, 3138, 3140, 3145, 3146, 3139, 3141, 3153, 3154, 3155, 3127, 3129, 3131, 3132, 3130, 3131, 3161, 3190, 3200, 3179, 3178, 3169, 3167, 3171, 3191, 3205, 3203, 3208, 3214, 3215, 3217, 3218, 3224, 3228, 3229, 3215, 3214, 3237, 3246, 3223, 3220, 3209, 3207, 3220, 3233, 3238, 3234, 3211, 3216, 3220, 3221, 3216, 3219, 3218, 3217, 3221, 3223, 3228, 3224, 3244, 3245, 3251, 3250, 3256, 3257, 3258, 3262, 3264, 3265, 3263, 3272, 3274, 3255, 3254, 3266, 3267, 3264, 3267, 3280, 3282};
    }

}
