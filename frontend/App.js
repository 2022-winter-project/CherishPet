import { StatusBar } from "expo-status-bar";
import {
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Dimensions,
} from "react-native";
import { Image } from "react-native";

const { width: SCREEN_WIDTH } = Dimensions.get("window");

export default function App() {
  return (
    <View style={styles.container}>
      <View>
        <Image style={styles.logo} source={require("./icon/logo_white.png")} />
      </View>
      <Text style={styles.appText}>CHERISH PET!</Text>
      <StatusBar style="auto" />
      <View style={styles.startBtn}>
        <TouchableOpacity>
          <Text style={styles.startText}>시작하기</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#D8E1FF",
    alignItems: "center",
    justifyContent: "center",
  },
  appText: {
    fontSize: 20,
    fontWeight: "700",
  },
  startBtn: {
    backgroundColor: "white",
    alignItems: "center",
    paddingBottom: 7,
    paddingTop: 7,
    borderRadius: 50,
    width: SCREEN_WIDTH * 0.3,
    shadowColor: "#000000",
    shadowOpacity: 0.3,
    shadowOffset: { width: 2, height: 2 },
    elevation: 3,
    marginTop: SCREEN_WIDTH * 0.2,
  },
  startText: { fontSize: 16 },
  logo: {
    resizeMode: "contain",
    width: SCREEN_WIDTH * 0.4,
    height: SCREEN_WIDTH * 0.5,
  },
});
