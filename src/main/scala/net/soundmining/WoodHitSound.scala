package net.soundmining

import net.soundmining.ConcreteMusic10Common._
import net.soundmining.Generative.{WeightedRandom, randomRange}
import net.soundmining.modular.ModularSynth.{relativePercControl, staticControl}

object WoodHitSound {
  val WOOD_HIT_1_RATE = 613.0 / 1044.0
  // 613 (peak), 1044, 1955, 3052, 4684, 6199
  val WOOD_HIT_1_VARIANTS: Map[SoundVariant, Seq[(Double, Double, Double) => Unit]] = Map(
    CLEAN_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    LOW_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp * 4)
        .lowPass(staticControl(613 * WOOD_HIT_1_RATE))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 40, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp * 2)
        .lowPass(staticControl(613))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 40, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    MIDDLE_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(613))
        .lowPass(staticControl(1044))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(1044))
        .lowPass(staticControl(1955))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(1955))
        .lowPass(staticControl(3052))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    HIGH_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(3052))
        .lowPass(staticControl(4684))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(4684))
        .lowPass(staticControl(6199))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_1)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(6199))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE))
  )

  object WoodHit1HarmonicVariants {

    def makeSpectrum(rate: Double, fundamental: Double = 613): Seq[Double] =
      Spectrum.makeSpectrum2(fundamental * rate, SPECTRUM_RATE, 50)

    val SPECTRUM_RATE = math.pow(WOOD_HIT_1_RATE, 3)

    val LOWEST_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_1_RATE, 4))

    val LOW_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_1_RATE, 3))

    val MIDDLE_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_1_RATE, 2))

    val HIGH_SPECTRUM = makeSpectrum(WOOD_HIT_1_RATE)

    val variants: Map[SoundVariant, Seq[(Double, Int, Double, Double, Double, Double) => Unit]] = Map(
      LOW_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .pulse(staticControl(LOWEST_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(LOWEST_SPECTRUM(note + 2)))
            .ring(staticControl(LOWEST_SPECTRUM(note + 4)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, outputBus = 46, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        },
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .sine(staticControl(LOW_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(LOW_SPECTRUM(note + 1)))
            .ring(staticControl(LOW_SPECTRUM(note + 2)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, outputBus = 48, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      ),
      MIDDLE_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .saw(staticControl(MIDDLE_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(MIDDLE_SPECTRUM(note + 0)))
            .ring(staticControl(MIDDLE_SPECTRUM(note + 1)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, outputBus = 50, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      ),
      HIGH_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .triangle(staticControl(HIGH_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(HIGH_SPECTRUM(note + 3)))
            .ring(staticControl(HIGH_SPECTRUM(note + 6)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, outputBus = 52, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      )
    )
  }

  // Wood hit 2 436 (peak), 977, 1443, 2308, 4603, 5312
  object WoodHit2HarmonicVariants {
    def makeSpectrum(rate: Double, fundamental: Double = 436): Seq[Double] =
      Spectrum.makeSpectrum2(fundamental * rate, SPECTRUM_RATE, 50)

    val SPECTRUM_RATE = math.pow(WOOD_HIT_2_RATE, 2)

    val LOWEST_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_2_RATE, 3))

    val LOW_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_2_RATE, 2))

    val MIDDLE_SPECTRUM = makeSpectrum(math.pow(WOOD_HIT_2_RATE, 1))

    val HIGH_SPECTRUM = makeSpectrum(WOOD_HIT_2_RATE / WOOD_HIT_2_RATE)

    val variants: Map[SoundVariant, Seq[(Double, Int, Double, Double, Double, Double) => Unit]] = Map(
      LOW_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .pulse(staticControl(LOWEST_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(LOWEST_SPECTRUM(note + 2)))
            .ring(staticControl(LOWEST_SPECTRUM(note + 4)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        },
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .sine(staticControl(LOW_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(LOW_SPECTRUM(note + 1)))
            .ring(staticControl(LOW_SPECTRUM(note + 2)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      ),
      MIDDLE_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .triangle(staticControl(MIDDLE_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(MIDDLE_SPECTRUM(note + 0)))
            .ring(staticControl(MIDDLE_SPECTRUM(note + 1)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      ),
      HIGH_SOUND -> Seq(
        (start, note, amp, attackTime, duration, pan) => {
          synthPlayer()
            .saw(staticControl(HIGH_SPECTRUM(note)), relativePercControl(0.0001, amp, attackTime, Left(Seq(0, 0))))
            .ring(staticControl(HIGH_SPECTRUM(note + 3)))
            .ring(staticControl(HIGH_SPECTRUM(note + 6)))
            .pan(staticControl(pan))
            .playWithDuration(start, duration, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)
        }
      )
    )
  }

  lazy val WOOD_HIT_1_MIXES = SoundMixes(
    WOOD_HIT_1, WOOD_HIT_1_VARIANTS, Map(
      LOW_SOUND -> Map(
        LOW_SOUND -> WeightedRandom(Seq((1, 0.6), (2, 0.4)))),
      LOW_MIDDLE_SOUND -> Map(
        LOW_SOUND -> WeightedRandom(Seq((0, 0.1), (1, 0.6), (2, 0.3))),
        MIDDLE_SOUND -> WeightedRandom(Seq((0, 0.2), (1, 0.3), (2, 0.4), (3, 0.1)))),
      MIDDLE_SOUND -> Map(
        MIDDLE_SOUND -> WeightedRandom(Seq((1, 0.2), (2, 0.6), (3, 0.2)))),
      MIDDLE_HIGH_SOUND -> Map(
        MIDDLE_SOUND -> WeightedRandom(Seq((0, 0.5), (1, 0.4), (2, 0.1))),
        HIGH_SOUND -> WeightedRandom(Seq((1, 0.1), (2, 0.8), (3, 0.1)))),
      HIGH_SOUND -> Map(
        HIGH_SOUND -> WeightedRandom(Seq((1, 0.2), (2, 0.6), (3, 0.2))))))

  val WOOD_HIT_2_RATE = 436.0 / 977.0
  // 436 (peak), 977, 1443, 2308, 4603, 5312
  val WOOD_HIT_2_VARIANTS: Map[SoundVariant, Seq[(Double, Double, Double) => Unit]] = Map(
    CLEAN_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    LOW_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp * 4)
        .lowPass(staticControl(436 * WOOD_HIT_2_RATE))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 40, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp * 2)
        .lowPass(staticControl(436))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 40, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    MIDDLE_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(436))
        .lowPass(staticControl(977))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(977))
        .lowPass(staticControl(1443))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(1443))
        .lowPass(staticControl(2308))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 42, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE)),
    // 436 (peak), 977, 1443, 2308, 4603, 5312
    HIGH_SOUND -> Seq(
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(2308))
        .lowPass(staticControl(4603))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(4603))
        .lowPass(staticControl(5312))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE),
      (start, amp, pan) => synthPlayer(WOOD_HIT_2)
        .playMono(1.0 + randomRange(-0.001, 0.001), amp)
        .highPass(staticControl(5312))
        .pan(staticControl(pan + randomRange(-0.1, 0.1)))
        .play(start, outputBus = 44, shouldWriteToScore = ConcreteMusic10Common.WRITE_TO_SCORE))
  )

  lazy val WOOD_HIT_2_MIXES = SoundMixes(
    WOOD_HIT_2, WOOD_HIT_2_VARIANTS,
    Map(
      LOW_SOUND -> Map(
        LOW_SOUND -> WeightedRandom(Seq((1, 0.6), (2, 0.4)))),
      LOW_MIDDLE_SOUND -> Map(
        LOW_SOUND -> WeightedRandom(Seq((0, 0.1), (1, 0.6), (2, 0.3))),
        MIDDLE_SOUND -> WeightedRandom(Seq((0, 0.2), (1, 0.3), (2, 0.4), (3, 0.1)))),
      MIDDLE_SOUND -> Map(
        MIDDLE_SOUND -> WeightedRandom(Seq((1, 0.2), (2, 0.6), (3, 0.2)))),
      MIDDLE_HIGH_SOUND ->  Map(
        MIDDLE_SOUND -> WeightedRandom(Seq((0, 0.5), (1, 0.4), (2, 0.1))),
        HIGH_SOUND -> WeightedRandom(Seq((1, 0.1), (2, 0.8), (3, 0.1)))),
      HIGH_SOUND -> Map(
        HIGH_SOUND -> WeightedRandom(Seq((1, 0.2), (2, 0.6), (3, 0.2))))))

  lazy val WOOD_HIT_MIXES = MaterialMixes(Seq(WOOD_HIT_1_MIXES, WOOD_HIT_2_MIXES))
}
