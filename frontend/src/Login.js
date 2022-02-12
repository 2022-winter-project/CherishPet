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

export default function Login({ navigation }) {
  return (
    <View style={styles.container}>
      <StatusBar style="auto" />
      <View style={styles.head}>
        <Image style={styles.logo} source={require("../icon/logo_white.png")} />
        <Text style={styles.appText}>CHERISH PET!</Text>
      </View>
      <View style={styles.login}></View>
      <View></View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
    alignItems: "center",
  },
  head: {
    paddingTop: 50,
    backgroundColor: "#D8E1FF",
    width: SCREEN_WIDTH,
    height: SCREEN_WIDTH * 0.25,
    alignItems: "center",
  },
  appText: {
    fontSize: 20,
    fontWeight: "700",
  },
  login: {},
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
    width: SCREEN_WIDTH * 0.2,
    height: SCREEN_WIDTH * 0.2,
  },
});
